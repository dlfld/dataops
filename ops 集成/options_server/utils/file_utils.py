import os
import uuid

import joblib
import pandas as pd
import requests
from icecream import ic

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

    @staticmethod
    def save_params(out_func_data):
        """
        获取预处理方法的返回数据并保存到txt文件
        :param out_func_data: 预处理的返回值
        :return: FileMessage对象
        """
        file_name = f'{uuid.uuid1()}.params'
        file_full_path = f'{ConfigGet.get_data_file_path()}/{file_name}'
        joblib.dump(out_func_data, file_full_path)
        file_message = FileMessage(file_full_path=file_full_path, file_name=file_name)
        return file_message


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

    @staticmethod
    def read_params(items, read_params_func=joblib.load):
        """
        读取数据文件的方法
        :param items: 元数据对象列表
        :param read_params_func: 读取数据文件的方法 用户自己传进来默认就是直接按照变量的形式读取
        :return:
        """
        data = []
        for item in items:
            file_full_path = item['location']
            file_download_path = item['downloadUrl']
            # 判断文件是否存在
            exists = os.path.exists(file_full_path)
            # 获取文件名
            file_name = str(file_download_path).split("data_download/")[1]
            # 如果文件不存在的话
            if not exists:
                """
                    如果文件不存在的话就下载文件到本地然后再读取文件
                """
                ic("文件不存在，开始下载")
                down_res = requests.get(file_download_path)
                ic(f"文件：{file_name},下载成功")
                # 拼接文件保存的路径
                data_save_path = ConfigGet.get_data_file_path() + "/" + file_name
                with open(data_save_path, 'wb') as file:
                    file.write(down_res.content)
                # 重新赋值文件的路径
                file_full_path = data_save_path

            item_data = read_params_func(file_full_path)
            data.append(item_data)
        return data
