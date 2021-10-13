import numpy as np
from icecream import ic

from aop.data_func import func_config
import re


# 替换一些字符，所有字母变为小写
def clean_str(string):
    """
    Tokenization/string cleaning for all datasets except for SST.
    Original taken from https://github.com/yoonkim/CNN_sentence/blob/master/process_data.py
    """
    string = re.sub(r"[^A-Za-z0-9(),!?\'\`]", " ", string)
    string = re.sub(r"\'s", " \'s", string)
    string = re.sub(r"\'ve", " \'ve", string)
    string = re.sub(r"n\'t", " n\'t", string)
    string = re.sub(r"\'re", " \'re", string)
    string = re.sub(r"\'d", " \'d", string)
    string = re.sub(r"\'ll", " \'ll", string)
    string = re.sub(r",", " , ", string)
    string = re.sub(r"!", " ! ", string)
    string = re.sub(r"\(", " \( ", string)
    string = re.sub(r"\)", " \) ", string)
    string = re.sub(r"\?", " \? ", string)
    string = re.sub(r"\s{2,}", " ", string)
    return string.strip().lower()


def load_pos_neg_data(file_full_path):
    """
    读取pos和neg的数据并返回
    :param file_full_path:
    :return:
    """
    pos_data = "static/cnn_data/pos.txt"
    neg_data = "static/cnn_data/neg.txt"
    # 读入样本，将样本放入list中
    pos = list(open(pos_data, "r", encoding='utf-8').readlines())
    # strip()移除字符串头尾指定的字符（默认为空格或换行符）或字符序列
    pos = [s.strip() for s in pos]

    neg = list(open(neg_data, "r", encoding='utf-8').readlines())
    neg = [s.strip() for s in neg]

    x = pos + neg
    x = [clean_str(_) for _ in x]
    x = np.array(x)

    pos_label = [[0] for _ in pos]
    neg_label = [[1] for _ in neg]

    y = np.concatenate([pos_label, neg_label], axis=0)  # axis=0 按行拼接
    y = np.array(y)
    # y = tf.concat(0, [pos_label, neg_label]) 也可以这样写 与上一行作用相同
    return [x, y]


@func_config(
    data=dict({
        "optUrl": "/cnn_twitter_data_init",
        "optDesc": "cnn_初始化数据",  # 模块功能简介
        "optName": "cnn对twitter数据进行分类时，对数据的初始化工作",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after cnn  init twitter"  # 经过处理之后的desc
    }),
    pre_handle_adapter=load_pos_neg_data
)
def init_data(data):
    """
     加载pos 和neg的数据œ
    :param data: 读取的pos和neg的数据  格式：[x,y]
    :return:
    """
    ic(data)
