import pandas.io.json
from pandas.io.json import json_normalize
from pydantic import BaseModel

from aop.data_func import func_config
from utils.router_utils import get_router

router = get_router()
import pickle


class Params(BaseModel):
    items: list = None


'''
1。装饰器读目录的数据 输入方法中
2. 节点数据格式检查 -> 在装饰器中申明
'''
import pandas as pd
from fastai.tabular.all import *
import json


@func_config(dict({"optUrl": "/processing", "optDesc": "数据处理", "optName": "处理demo"}))
@router.post('/processing')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "start desc":
            X = item['object']
            cols = X.pop(0)
            df = pd.DataFrame(X, columns=cols)
            cont, cat = cont_cat_split(df, max_card=10,
                                       dep_var=['死亡', '严重心血管不良', '肺部并发症', '术后谵妄', '卒中', '中重度疼痛', '术后恶心呕吐_结局', 'ICU',
                                                '急性肾损伤'])
            for column in cont:
                mean_val = df[column].median()
                df[column].fillna(mean_val, inplace=True)
            ''''
            在此处写自己的计算代码
            封装成temp的格式返回即可
            '''
            df_dic = {"cols": df.columns.values.tolist(),
                      "values": df.values.tolist()}
            temp = {
                'desc': "after processing .",
                'version': 0,
                'object': {
                    "df": df_dic
                }
            }
            params.items.append(temp)
    return params
