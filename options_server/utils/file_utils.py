import uuid
import pandas as pd
from pandas import DataFrame
from utils.config_parse_util import ConfigGet


class FileWriters:
    """
    类说明：写文件的工具类
    """

    @staticmethod
    def write_data_frame_csv(data_frame):
        """
        把DataFrame写入csv文件
        :param data_frame:
        :return:file_full_path  保存文件的路径 file_name 文件名 , 顺序不能乱
        """
        file_name = f'{uuid.uuid1()}.csv'
        file_full_path = f'{ConfigGet.get_data_file_path()}/{file_name}'
        data_frame.to_csv(file_full_path)
        return {
            "file_full_path": file_full_path,
            "file_name": file_name
        }


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
