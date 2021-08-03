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
import pandas as pd
from fastai.tabular.all import *

@func_config(dict({"optUrl": "/predic", "optDesc": "病人预测", "optName": "预测demo"}))
@router.post('/predic')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "predic desc":
            X = item['object']
            names = X[0]
            values = X[1]
            x = pd.Series(values, index=names, dtype=float)
            saved_learn = load_learner('static/models/test.pkl')
            row, clas, probs = saved_learn.predict(x)
            res = {
                "row":row,
                "clas":clas,
                "probs":probs
            }
            ''''
            在此处写自己的计算代码
            封装成temp的格式返回即可
            '''
            temp = {
                'desc': "after start sum desc",
                'version': 0,
                'object':res
            }
            params.items.append(temp)
    return params
