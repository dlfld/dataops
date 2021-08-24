import numpy as np
import pandas as pd
from icecream import ic

from aop.data_func import func_config
from utils.file_utils import FileReaders, FileWriters


def handle_items(items):
    """
    读取数据文件的方法
    :param items:
    :return:
    """
    data = []
    for item in items:
        ic(item)
        res_data = FileReaders.read_csv(item['location'])
        data.append(np.array(res_data))
    return data


def save_file_func(data):
    data_frame = pd.DataFrame(data)
    return FileWriters.write_data_frame_csv(data_frame)


@func_config(
    data=dict({
        "optUrl": "/handle_add",
        "optDesc": "执行加法操作",  # 模块功能简介
        "optName": "加法",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after start desc"  # 经过处理之后的desc
    }),
    pre_handle_adapter=handle_items,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=save_file_func  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
)
def handle_add(data):
    ic("进来了handle_add")
    ic(data)
    res = ""
    if len(data) == 1:
        res = np.dot(data[0], data[0])
    elif len(data) == 2:
        res = np.dot(data[1], data[0])
    ic(res)
    return res

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
