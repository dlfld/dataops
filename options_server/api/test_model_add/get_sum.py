
from pydantic import BaseModel

from aop.data_func import func_config
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


@func_config(dict({"optUrl": "/sum  ", "optDesc": "求和", "optName": "求和"}))
@router.post('/sum')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            ''''
            在此处写自己的计算代码
            封装成temp的格式返回即可
            '''
            temp = {
                'desc': "after start sum desc",
                'version': 0,
                'object': item['object']
            }
            params.items.append(temp)
    return params