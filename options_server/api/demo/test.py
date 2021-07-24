from pydantic import BaseModel

from aop.data_func import func_config
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None

'''
1。装饰器读目录的数据 输入方法中
2. 节点数据格式检查 -> 在装饰器中申明
'''

@func_config(dict({"optUrl": "/demo", "optDesc": "demo", "optName": "一个demo"}))
@router.post('/demo')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            print("aaa")
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
