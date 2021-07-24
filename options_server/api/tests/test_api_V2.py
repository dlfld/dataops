from loguru import logger
from pydantic import BaseModel

from aop.data_func import func_config
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


@func_config(dict({"optUrl": "/add", "optDesc": "执行加法操作", "optName": "加法"}))
@router.post('/add')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            print(item)
            temp = {
                'desc': "after start add desc",
                'version': 0,
                'object': item['object']
            }
            params.items.append(temp)
    return params
    # return "".join([item for item in params.items])+"add "


@func_config(dict({"optUrl": "/min", "optDesc": "实现减操作", "optName": "减法"}))
@router.post('/min', summary="实现减操作")
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            temp = {
                'desc': "after start min desc",
                'version': 0,
                'object': item['object']
            }
            params.items.append(temp)
    return params


@func_config(dict({"optUrl": "/rid", "optDesc": "执行乘法操作", "optName": "乘法"}))
@router.post('/rid')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            temp = {
                'desc': "after start rid  desc",
                'version': 0,
                'object': item['object']
            }
            params.items.append(temp)
    return params


@func_config(dict({"optUrl": "/div", "optDesc": "执行除法操作", "optName": "除法"}))
@router.post('/div')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            temp = {
                'desc': "after start div desc",
                'version': 0,
                'object': item['object']
            }
            params.items.append(temp)
    return params


@router.post('/ ')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            item['object'] = item['object'] + " add"
            item['version'] = item['version'] + 1
    return params
