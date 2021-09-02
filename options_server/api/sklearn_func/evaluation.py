import joblib
from icecream import ic
from sklearn.metrics import accuracy_score

from aop.data_func import func_config
from utils.file_utils import FileReaders, FileWriters


def read_get_acc_params(items):
    data = []
    for item in items:
        file_full_path = item['location']
        item_data = FileReaders.read_params(file_full_path)
        data.append(item_data)
    return data


@func_config(
    data=dict({
        "optUrl": "/get_acc",
        "optDesc": "获取新闻分类模型准确率",  # 模块功能简介
        "optName": "获取新闻分类模型准确率",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after get_acc"  # 经过处理之后的desc
    }),
    pre_handle_adapter=read_get_acc_params,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=FileWriters.save_params
)  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
def get_acc(data):
    ic("进入get_acc")
    ic(len(data))
    data_set = data[0]
    model = data[1]
    X_test = data_set['X_test_tfidf']
    Y_test = data_set['Y_test']
    y_pred = model.predict(X_test.toarray())
    acc = accuracy_score(y_true=Y_test, y_pred=y_pred)
    ic(acc)
    return acc
