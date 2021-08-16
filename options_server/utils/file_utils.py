import uuid
import pandas as pd
from pandas import DataFrame
from utils.config_parse_util import get_config
import os


def judge_file_path(path):
    """
    判断路径是否存在，如果不存在就创建
    :param path: 文件路径
    """
    if os.path.exists(path):
        return
    os.makedirs(path)


class FileWriters:
    """
    类说明：写文件的工具类
    """

    @staticmethod
    def write_data_frame_csv(data_frame):
        """
        把DataFrame写入csv文件
        :param data_frame:
        :return: 保存文件的路径
        """
        file_full_path = f'{get_config("data_upload", "data_save_path")}/{uuid.uuid1()}.csv'
        data_frame.to_csv(file_full_path)
        return file_full_path


class FileReaders:
    """
    类说明：读取文件的工具类
    """

    # 读取csv文件
    @staticmethod
    def read_csv(file_full_path) -> DataFrame:
        return pd.read_csv(file_full_path)  # 读取csv文件

# data = FileReaders.read_csv("/Users/dailinfeng/Desktop/test_data.csv")
# print(data)
