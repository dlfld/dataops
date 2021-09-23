import requests
import uvicorn
from fastapi import FastAPI, APIRouter
from icecream import ic
from starlette.staticfiles import StaticFiles

from aop.file_path_cd import file_path_detect
from utils.config_parse_util import ConfigGet
from utils.router_utils import get_router

import sys
import os

curPath = os.path.abspath(os.path.dirname(__file__))
rootPath = os.path.split(curPath)[0]
sys.path.append(rootPath)
from api import *
from base_api import *

app = FastAPI()


def config():
    # 加载api
    app.include_router(get_router())
    # 加载静态资源
    app.mount("/static", StaticFiles(directory="static"), name="static")


def connect_server_center():
    """
    在注册中心进行注册
    :return:
    """
    host_split = ConfigGet.get_server_host().split(':')
    ip = host_split[0] + host_split[1]
    port = host_split[2]
    data = {
        "ip": ip,
        "port": port,
        "serviceName": "客户端名"
    }
    res = requests.post(url=ConfigGet.get_server_center_host() + "/service_center/register", json=data).json()
    ic(res)


if __name__ == '__main__':
    # 配置
    config()
    # 连接注册中心
    connect_server_center()
    # 检测文件路径是否存在，如果不存在就创建
    file_path_detect()
    # 获取路由
    router = APIRouter()
    uvicorn.run(app=app, host='0.0.0.0')
