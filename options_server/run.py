import uvicorn
from fastapi import FastAPI, APIRouter
from starlette.staticfiles import StaticFiles
import sys
import os

curPath = os.path.abspath(os.path.dirname(__file__))
rootPath = os.path.split(curPath)[0]
sys.path.append(rootPath)
app = FastAPI()

from api.options import options_api
from api.tests import test_api

def config():
    # 加载api
    app.include_router(options_api.router)
    app.include_router(test_api.router)
    # 加载静态资源
    app.mount("/static", StaticFiles(directory="static"), name="static")



if __name__ == '__main__':
    config()
    router = APIRouter()
    uvicorn.run(app=app, host='0.0.0.0')
