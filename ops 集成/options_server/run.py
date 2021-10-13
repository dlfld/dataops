import threading

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

from utils.server_center import connect_server_center

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


def start_connect():
    """
    在注册中心进行注册
    :return:
    """
    threading.Thread(target=connect_server_center).start()
    ic("开始心跳连接注册中心")


if __name__ == '__main__':
    # 配置
    config()
    # 连接注册中心
    start_connect()
    # 检测文件路径是否存在，如果不存在就创建
    file_path_detect()
    # 获取路由
    router = APIRouter()
    uvicorn.run(app=app, host='0.0.0.0')
