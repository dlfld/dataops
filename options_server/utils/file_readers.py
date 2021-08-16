'''
读取文件的reader
'''

import pandas as pd
from pandas import DataFrame


class FileWriters:
    pass
    # @staticmethod
    # def read_csv(file_full_path) -> DataFrame:
    #     return pd.read_csv(file_full_path)  # 读取csv文件


class FileReaders:
    # 读取csv文件
    @staticmethod
    def read_csv(file_full_path) -> DataFrame:
        return pd.read_csv(file_full_path)  # 读取csv文件

# data = FileReaders.read_csv("/Users/dailinfeng/Desktop/test_data.csv")
# print(data)
