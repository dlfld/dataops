import uvicorn
from fastapi import FastAPI, APIRouter
from starlette.staticfiles import StaticFiles

import os

from aop.file_path_cd import file_path_detect
from utils.router_utils import get_router

import sys

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


if __name__ == '__main__':
    config()
    file_path_detect()  # 检测文件路径是否存在，如果不存在就创建
    router = APIRouter()
    uvicorn.run(app=app, host='0.0.0.0')
