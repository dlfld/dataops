import uuid

from icecream import ic
from pydantic import BaseModel

from pojo.FileMessage import FileMessage
from utils.config_parse_util import ConfigGet
from utils.router_utils import get_router


class Params(BaseModel):
    items: list = None


class Options:
    options = []


router = get_router()

'''

这是方法上面的注解，接收的是data的字典
目前版本主要是注解 url、desc、name 用来自动生成options的列表，因为后期可能会加其他字段，因此选用字典的形式进行穿参

工作：
    获取url和desc并添加到现在维护的列表当中去
'''


def func_config(data: dict, pre_handle_adapter=lambda x: x,
                after_handle_adapter=lambda x: FileMessage()):
    """
    模块方法上的装饰器方法
    :param data: 配置
    :param pre_handle_adapter: 在进入计算节点之前进行数据处理（读取数据文件+格式转换） 返回值应该是数据
    :param after_handle_adapter: 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换） 返回值是对象FileMessage
    :return:
    """
    data['optUrl'] = uuid.uuid1()  # 通过uuid的方式随机出请求的url
    print("进来了func_config，他的参数有：", data)
    Options.options.append(data)

    def parser_data(func):
        # fast api的构建
        @router.post(data['optUrl'], summary=data['optName'])
        async def wrapper(params: Params):
            ic(params)
            # 调用方法处理之前的数据预处理
            in_func_data = pre_handle_adapter(params.items)
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
                "hosts": []
            }
            # 把返回对象添加到返回列表中
            params.items.append(res_dict)
            return params

        return wrapper

    return parser_data


def func_config_(data: dict, read_file_func, save_file_func, pre_handle_adapter=lambda x: x,
                 after_handle_adapter=lambda x: x):
    """

    :param data: 回传前端以及判断的参数
    :param read_file_func:  读取数据文件的读取方法
    :param save_file_func:  将数据存入文件的方法
    :param pre_handle_adapter:  预处理数据的方法,如果不传的话就是不处理
    :param after_handle_adapter: 数据处理完成之后需要执行的方法，用来转换处理之后的数据
    :return:
    """
    data['optUrl'] = uuid.uuid1()  # 通过uuid的方式随机出请求的url
    print("进来了func_config，他的参数有：", data)
    Options.options.append(data)

    def parser_data(func):
        # fast api的构建
        @router.post(data['optUrl'], summary=data['optName'])
        async def wrapper(params: Params):
            ic(params)
            desc = data['desc']
            for item in params.items:
                if item['desc'] == desc:
                    ic(item)
                    # 根据文件的路径对文件内容进行读取（使用传入的方法）
                    file_content = read_file_func(item['location'])
                    # 后面如果要添加数据格式转换的adapter就在下一步进行调用
                    in_func_data = pre_handle_adapter(file_content)
                    # 对数据进行adapter处理之后的返回值
                    out_func_data = func(in_func_data)
                    # 对返回的数据进行格式化的处理
                    handle_res = after_handle_adapter(out_func_data)
                    # 调用传进来的方法保存结果的方法 返回值是保存文件的全路径
                    file_save_res = save_file_func(handle_res)
                    # 如果返回的desc和取参数的desc相同，表示没有产生新的数据，只是对原来的数据进行操作
                    return_version = int(item['version'])
                    if data['return_desc'] == desc:
                        # 如果需要修改版本号，那么就版本号加一
                        if data['changeVersion']:
                            return_version = int(item['version']) + 1
                    # 获取保存文件的文件名
                    save_file_name = file_save_res['file_name']
                    # 获取文件的下载链接
                    downloadUrl = f'{ConfigGet.get_server_host()}/data_download/{save_file_name}'
                    # 封装返回对象
                    res_dict = {
                        "desc": data['return_desc'],  # 配置的desc取出来作为返回值的desc
                        "version": return_version,  # 版本号
                        "location": file_save_res['file_full_path'],  # 文件全路径（加上文件名的）
                        "fileName": save_file_name,  # 文件名
                        "downloadUrl": downloadUrl,  # 下载文件的链接
                        "hosts": []
                    }
                    # 把返回对象添加到返回列表中
                    params.items.append(res_dict)
            return params

        return wrapper

    return parser_data
