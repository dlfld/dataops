import uuid
import pandas as pd
from icecream import ic
from pandas import DataFrame

from pojo.FileMessage import FileMessage
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
        return FileMessage(file_full_path, file_name)


class FileReaders:
    """
    类说明：读取文件的工具类
    """

    # 读取csv文件
    @staticmethod
    def read_csv(file_full_path):
        res = pd.read_csv(file_full_path, index=None)
        ic(res)
        return res  # 读取csv文件

    @staticmethod
    def read_csv_by_csv(file_full_path):
        res = []
        import csv
        with open(file_full_path, 'r') as f:
            reader = csv.reader(f)
            for row in reader:
                row2 = []
                for i in row:
                    row2.append(float(i))
                res.append(row2)
        ic(res)
        return res

# data = FileReaders.read_csv("/Users/dailinfeng/Desktop/test_data.csv")
# print(data)
