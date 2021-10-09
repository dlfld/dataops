import joblib
from icecream import ic
from sklearn import svm
from sklearn.ensemble import BaggingClassifier
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RBF
from sklearn.neighbors import KNeighborsClassifier

from aop.data_func import func_config
from utils.file_utils import FileReaders, FileWriters


@func_config(
    data=dict({
        "optUrl": "/linner_svc",
        "optDesc": "使用LinnerSVC进行模型训练",  # 模块功能简介
        "optName": "LinnerSVC",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after LinnerSVC"  # 经过处理之后的desc
    }),
    pre_handle_adapter=FileReaders.read_params,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=FileWriters.save_params)  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
def LinnerSVC(data):
    ic("进入LinnerSVC")
    # ic(data)
    X_train, Y_train = data['X_train_tfidf'], data['Y_train']
    """
    使用传入的方法进行训练并得出结果
    :param data_list: 数据列表
    :param model_func: 模型方法
    :return:
    """
    model_func = svm.LinearSVC()
    model_func.fit(X_train.toarray(), Y_train)
    return model_func


@func_config(
    data=dict({
        "optUrl": "/SVC",
        "optDesc": "使用SVC进行模型训练",  # 模块功能简介
        "optName": "SVC",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after SVC"  # 经过处理之后的desc
    }),
    pre_handle_adapter=FileReaders.read_params,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=FileWriters.save_params)  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
def SVC(data):
    X_train, Y_train = data['X_train_tfidf'], data['Y_train']
    model_func = svm.SVC()
    model_func.fit(X_train.toarray(), Y_train)
    return model_func


@func_config(
    data=dict({
        "optUrl": "/Bagging",
        "optDesc": "使用Bagging进行模型训练",  # 模块功能简介
        "optName": "Bagging",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after Bagging"  # 经过处理之后的desc
    }),
    pre_handle_adapter=FileReaders.read_params,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=FileWriters.save_params)  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
def Bagging(data):
    X_train, Y_train = data['X_train_tfidf'], data['Y_train']
    model_func = BaggingClassifier(KNeighborsClassifier(), max_samples=0.5, max_features=0.5)
    model_func.fit(X_train.toarray(), Y_train)
    return model_func


@func_config(
    data=dict({
        "optUrl": "/GPC",
        "optDesc": "使用GPC进行模型训练",  # 模块功能简介
        "optName": "GPC",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after GPC"  # 经过处理之后的desc
    }),
    pre_handle_adapter=FileReaders.read_params,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=FileWriters.save_params)  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
def GPC(data: list):
    X_train, Y_train = data['X_train_tfidf'], data['Y_train']
    kernel = 1.0 * RBF(1.0)
    model_func = GaussianProcessClassifier(kernel=kernel, random_state=0)
    model_func.fit(X_train.toarray(), Y_train)
    return model_func
