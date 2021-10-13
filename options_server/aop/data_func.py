import uuid

import joblib
from icecream import ic
from pydantic import BaseModel

from pojo.FileMessage import FileMessage
from utils.config_parse_util import ConfigGet
from utils.file_utils import FileReaders, FileWriters
from utils.router_utils import get_router


class Params(BaseModel):
    items: list = None
    curNodeId: str = ""


class Options:
    options = []


router = get_router()
'''

这是方法上面的注解，接收的是data的字典
目前版本主要是注解 url、desc、name 用来自动生成options的列表，因为后期可能会加其他字段，因此选用字典的形式进行穿参

工作：
    获取url和desc并添加到现在维护的列表当中去
'''


def func_config(data: dict, pre_handle_adapter=joblib.load,
                after_handle_adapter=FileWriters.save_params):
    """
    模块方法上的装饰器方法
    :param data: 配置
    :param pre_handle_adapter: 在进入计算节点之前进行数据处理（读取数据文件+格式转换） 返回值应该是数据  参数是item对象列表
    :param after_handle_adapter: 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换） 返回值是对象FileMessage  输入是方法返回的数据，和文件保存路径
    :return:
    """
    # data['optUrl'] = f'/{str(uuid.uuid1())}'  # 通过uuid的方式随机出请求的url
    # data['optUrl'] = "/40054eca-040f-11ec-9669-1e00d10ae89a"  # 通过uuid的方式随机出请求的url
    fastapi_url = data['optUrl']
    data['optUrl'] = f"{ConfigGet.get_server_host()}{data['optUrl']}"

    ic(data['optUrl'])
    print("进来了func_config，他的参数有：", data)
    Options.options.append(data)

    def parser_data(func):
        # fast api的构建
        @router.post(fastapi_url, summary=data['optName'])
        async def wrapper(params: Params):
            ic(params)
            # 调用方法处理之前的数据预处理
            in_func_data = FileReaders.read_params(params.items,pre_handle_adapter)
            # 调用方法
            out_func_data = func(in_func_data)
            # 处理方法输出
            file_message = after_handle_adapter(out_func_data)
            # 封装返回对象
            downloadUrl = f'{ConfigGet.get_server_host()}/data_download/{file_message.file_name}'
            res_dict = {
                "desc": data['return_desc'],  # 配置的desc取出来作为返回值的desc
                "version": 0,  # 版本号
                "location": file_message.file_full_path,  # 文件全路径（加上文件名的）
                "fileName": file_message.file_name,  # 文件名
                "downloadUrl": downloadUrl,  # 下载文件的链接
                "hosts": [],
                "curNodeId": params.curNodeId  # 设置当前节点的ID
            }
            # 如果返回的文件不为空。把返回对象添加到返回列表中
            if file_message.file_name != "" and file_message.file_name is not None:
                params.items.append(res_dict)
            # ic(params)
            return params

        return wrapper

    return parser_data
