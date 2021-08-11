import configparser

'''
获取配置文件的值，title是[]里的内容，key是某一项的键
'''


def get_config(title: str, key: str):
    config = configparser.ConfigParser()
    config.read("static/config.ini")
    print(config.sections())
    return config.get(title, key)
