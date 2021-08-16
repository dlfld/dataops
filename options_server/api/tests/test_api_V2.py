import pandas as pd
from loguru import logger
from pydantic import BaseModel

from aop.data_func import func_config
from utils.file_readers import FileReaders
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


@func_config(
    data=dict({
        "optUrl": "/add",  # 功能模块的调用url  有个想法：这个url现在是人工配置的，实际上根本不需要人工配置，这个url对使用是无感的，只是为了调用，因此能否自动生成（前提是永远不会重复）
        "optDesc": "执行加法操作",  # 模块功能简介
        "optName": "加法",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc
        "changeVersion": False,  # 是否修改版本号
        "return_desc": "after start desc"  # 经过处理之后的desc
    }),
    read_file_func=FileReaders.read_csv  # 读取文件的方法

)
def handle_add(data_frame):
    print("进来了handle_add")
    print(data_frame)
    temp = {
        'desc': "after start add desc",
        'version': 0,
        'location': "asdasdasd",
        'host': []
    }
    return temp

# @func_config(dict({"optUrl": "/min", "optDesc": "实现减操作", "optName": "减法", "desc": "start desc"}))
# async def get_options(item):
#     return item


# @func_config(dict({"optUrl": "/rid", "optDesc": "执行乘法操作", "optName": "乘法"}))
# @router.post('/rid')
# async def get_options(params: Params):
#     for item in params.items:
#         if item['desc'] == "start desc":
#             temp = {
#                 'desc': "after start rid  desc",
#                 'version': 0,
#                 'object': item['object']
#             }
#             params.items.append(temp)
#     return params
#
#
# @func_config(dict({"optUrl": "/div", "optDesc": "执行除法操作", "optName": "除法"}))
# @router.post('/div')
# async def get_options(params: Params):
#     for item in params.items:
#         if item['desc'] == "start desc":
#             temp = {
#                 'desc': "after start div desc",
#                 'version': 0,
#                 'object': item['object']
#             }
#             params.items.append(temp)
#     return params
#
#
# @router.post('/ ')
# async def get_options(params: Params):
#     for item in params.items:
#         if item['desc'] == "start desc":
#             item['object'] = item['object'] + " add"
#             item['version'] = item['version'] + 1
#     return params
