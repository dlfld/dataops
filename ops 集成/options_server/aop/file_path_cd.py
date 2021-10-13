import os

from utils.config_parse_util import ConfigGet


def judge_file_path(path):
    """
    判断路径是否存在，如果不存在就创建
    :param path: 文件路径
    """
    if os.path.exists(path):
        return
    os.makedirs(path)


def file_path_detect():
    """
    检测当前所有的配置文件当中的路径，如果不存在就创建
    """
    judge_file_path(ConfigGet.get_data_file_path())
    judge_file_path(ConfigGet.get_model_save_path())
