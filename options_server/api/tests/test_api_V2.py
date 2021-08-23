import numpy as np
import pandas as pd
from loguru import logger
from pydantic import BaseModel

from aop.data_func import func_config
from utils.file_utils import FileReaders, FileWriters
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


@func_config(
    data=dict({
        "optDesc": "执行加法操作",  # 模块功能简介
        "optName": "加法",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc
        "return_desc": "after start desc"  # 经过处理之后的desc
    }),
    # pre_handle_adapter=lambda x: np.array(x),  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    # after_handle_adapter=lambda x: pd.DataFrame(x)  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
)
def handle_add(data_frame):
    print("进来了handle_add")
    print(data_frame)
    # 在这个地方操作dataframe对象
    return data_frame

#
# @func_config(
#     data=dict({
#         "optDesc": "执行加法操作",  # 模块功能简介
#         "optName": "加法",  # 展示在前端的模块名
#         "desc": "start desc",  # 需要的数据的desc
#         "changeVersion": False,  # 是否修改版本号
#         "return_desc": "after start desc"  # 经过处理之后的desc
#     }),
#     read_file_func=FileReaders.read_csv,  # 读取文件的方法
#     save_file_func=FileWriters.write_data_frame_csv,  # 写入文件的方法
#     pre_handle_adapter=lambda x: np.array(x),  # 在进入计算之前需要执行的数据格式转换操作
#     after_handle_adapter=lambda x: pd.DataFrame(x)  # 在计算完成之后需要进行的数据格式转换操作
# )
# def handle_add(data_frame):
#     print("进来了handle_add")
#     print(data_frame)
#     # 在这个地方操作dataframe对象
#     return data_frame
