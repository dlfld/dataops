from loguru import logger
from pydantic import BaseModel
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


@router.post('/add')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            '''
            
            
            '''
            print(item)
            temp = {
                'desc': "after start add desc",
                'version': 0,
                'object': item['object']
            }
            params.items.append(temp)
    return params
    # return "".join([item for item in params.items])+"add "


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
