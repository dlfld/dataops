from fastapi import APIRouter
from loguru import logger
from pydantic import BaseModel

router = APIRouter()


class Params(BaseModel):
    items: list = None


@router.post('/down')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            '''
            '''
            temp = {
                'desc': "after start add down",
                'version': 0,
                'object': item['object'] + " add"
            }
            params.items.append(temp)
    return params