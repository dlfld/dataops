"""
连接注册中心的具体实现方法
"""
import json
import threading
import time

import requests
from icecream import ic

from utils.config_parse_util import ConfigGet

from aop.data_func import Options


def connect_server_center():
    """
    连接注册中心并进行心跳连接
    :return:
    """
    sleepTime = 5
    while True:
        try:
            time.sleep(sleepTime)
            ip = ConfigGet.get_local_ip()
            port = ConfigGet.get_local_port()
            options = Options.options
            data = {
                "ip": ip,
                "port": port,
                "serviceName": "客户端名",
                "options": options
            }
            res = requests.post(url=ConfigGet.get_server_center_host() + "/service_center/register", json=data).text
            json_res = json.loads(res)
            ic(json_res)
            sleepTime = int(json_res['data'])
        except Exception as e:
            ic(e)
