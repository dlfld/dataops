from loguru import logger
from pydantic import BaseModel

from aop.data_func import func_config
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


@func_config(dict({"optUrl": "/add", "optDesc": "执行加法操作", "optName": "加法", "desc": "start desc"}))
def handle_add(item):
    print("进来了handle_add")
    print(item)

    temp = {
        'desc': "after start add desc",
        'version': 0,
        'location': "asdasdasd",
        'host': []
    }
    return temp


@func_config(dict({"optUrl": "/min", "optDesc": "实现减操作", "optName": "减法", "desc": "start desc"}))
async def get_options(item):
    return item


