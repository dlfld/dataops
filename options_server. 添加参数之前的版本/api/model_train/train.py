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


@func_config(dict({"optUrl": "/train", "optDesc": "模型训练", "optName": "训练demo"}))
@router.post('/train')
async def get_options(params: Params):
    for item in params.items:
        if item['desc'] == "after processing .":
            X = item['object']['df']
            cols = X["cols"]
            values = X["values"]
            df = pd.DataFrame(values, columns=cols, dtype=float)
            cont, cat = cont_cat_split(df, max_card=10,
                                       dep_var=['死亡', '严重心血管不良', '肺部并发症', '术后谵妄', '卒中', '中重度疼痛', '术后恶心呕吐_结局', 'ICU',
                                                '急性肾损伤'])
            splits = RandomSplitter(valid_pct=0.2)(range_of(df))

            def train_all(label, df):
                labels = ['死亡', '急性肾损伤', '术后谵妄', '卒中', '中重度疼痛', '术后恶心呕吐_结局', 'ICU', '肺部并发症', '严重心血管不良']
                labels.remove(label)
                df_train_data = df.drop(labels=labels, axis=1)
                df_train_data[label] = df_train_data[label].astype('category')
                to = TabularPandas(df_train_data, procs=[Categorify, Normalize, FillMissing],
                                   cat_names=cat,
                                   cont_names=cont,
                                   y_names=label,
                                   splits=splits,
                                   y_block=CategoryBlock)
                dls = to.dataloaders(bs=1024)
                learn = tabular_learner(dls)
                learn.fit_one_cycle(20)
                learn.export('static/models/test.pkl')
                print("111111111111111111111111111")

            train_all('肺部并发症', df)
            ''''
            在此处写自己的计算代码
            封装成temp的格式返回即可
            '''
    return params
