import numpy as np
import pandas as pd
from icecream import ic
from pydantic import BaseModel

from aop.data_func import func_config
from utils.file_utils import FileReaders, FileWriters
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


@func_config(
    data=dict({
        "optDesc": "使用numpy执行矩阵乘法操作",  # 模块功能简介
        "optName": "矩阵乘法",  # 展示在前端的模块名
        "desc": "float, float",  # 需要的数据的desc
        "changeVersion": False,  # 是否修改版本号
        "return_desc": "int",  # 经过处理之后的desc
        "save": False  # 是否永久存储
    }),
    read_file_func=FileReaders.read_csv,  # 读取文件的方法
    save_file_func=FileWriters.write_data_frame_csv,  # 写入文件的方法
    pre_handle_adapter=lambda x: np.array(x),  # 在进入计算之前需要执行的数据格式转换操作
    after_handle_adapter=lambda x: pd.DataFrame(x)  # 在计算完成之后需要进行的数据格式转换操作
)
def csv_matrix_pow_2(objects):
    res = np.dot(objects[0], objects[1])
    ic(res)
    return res
    # return data_frame


@func_config(
    data=dict({
        "optDesc": "使用numpy执行矩阵内积",  # 模块功能简介
        "optName": "矩阵内积",  # 展示在前端的模块名
        "desc": "csv_matrix_pow_2",  # 需要的数据的desc
        "changeVersion": False,  # 是否修改版本号
        "return_desc": "csv_matrix_vdot_2"  # 经过处理之后的desc
    }),
    read_file_func=FileReaders.read_csv,  # 读取文件的方法
    save_file_func=FileWriters.write_data_frame_csv,  # 写入文件的方法
    pre_handle_adapter=lambda x: np.array(x),  # 在进入计算之前需要执行的数据格式转换操作
    after_handle_adapter=lambda x: pd.DataFrame(x)  # 在计算完成之后需要进行的数据格式转换操作
)
def csv_matrix_vdot_2(data_frame):
    res = np.vdot(data_frame, data_frame)
    ic(res)
    return np.array([res])
    # return data_frame
