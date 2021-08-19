import configparser


def get_config(title: str, key: str):
    """
    获取配置文件的值，title是[]里的内容，key是某一项的键
    :param title: 配置文件中[]中的值
    :param key: 配置文件的key
    :return:
    """
    config = configparser.ConfigParser()
    config.read("static/config.ini")
    print(config.sections())
    return config.get(title, key)


class ConfigGet:
    """
    获取配置文件中的配置信息
    提供给用户调用
    """

    @staticmethod
    def get_data_file_path():
        """
        获取数据文件存储路径
        :return: 返回数据文件保存的位置
        """
        return get_config("data_upload", "data_save_path")

    @staticmethod
    def get_model_save_path():
        """
        获取模型保存的路径
        :return: 返回模型文件的保存位置
        """
        return get_config("models", "models")

    @staticmethod
    def get_server_host():
        """
        获取本机的ip和端口
        :return: 本机ip：端口
        """
        return get_config("data_upload", "host")
