import uuid

import numpy as np
import pandas as pd
from icecream import ic

from aop.data_func import func_config
from pojo.FileMessage import FileMessage
from utils.config_parse_util import ConfigGet
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
        res_data = FileReaders.read_csv_by_csv(item['location'])
        data.append(res_data)
    return data


def save_file_func(data):
    # ic(data)
    import csv
    file_name = f'{uuid.uuid1()}.csv'
    file_full_path = f'{ConfigGet.get_data_file_path()}/{file_name}'
    with open(file_full_path, 'w') as f:
        writer = csv.writer(f)
        for row in data:
            row2 = list(row)
            writer.writerow(row2)
    file_message = FileMessage(file_full_path=file_full_path, file_name=file_name)
    return file_message


@func_config(
    data=dict({
        "optUrl": "/min",
        "optDesc": "求矩阵中最小的数字",  # 模块功能简介
        "optName": "求min",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after start desc"  # 经过处理之后的desc
    }),
    pre_handle_adapter=handle_items,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=save_file_func  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
)
def handle_mul(data):
    # handle your data here
    # res = func()
    # return res
    return ""