from icecream import ic

from aop.data_func import func_config
from utils.file_utils import FileWriters


def load_txt_data(items):
    """
    读取文本文件并返回
    :param data_numbers:
    :param file_full_path: 文件全路径
    :return: 数据列表[[]]
    """
    data = []
    for item in items:
        with open(item['location'], "r") as f:
            item_data = []
            for s in f.readlines():
                item_data.append(s)
        data.append(item_data)
    return data[0][:1000]

    # data = []
    # with open(file_full_path, "r") as f:
    #     for s in f.readlines():
    #         data.append(s)
    # return data


@func_config(
    data=dict({
        "optUrl": "/pre_processing_news_data",
        "optDesc": "预处理新闻数据",  # 模块功能简介
        "optName": "预处理新闻数据",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "returnDesc": "after pre_processing_news_data"  # 经过处理之后的desc
    }),
    pre_handle_adapter=load_txt_data,  # 在进入计算节点之前进行数据处理（读取数据文件+格式转换）
    after_handle_adapter=FileWriters.save_params  # 在计算节点计算完成之后进行数据处理（写入数据文件+格式转换）
)
def pre_processing_news_data(data_list):
    """
    通过分隔符对数据进行划分
    :param data_list:
    :return:
    """
    ic("进入到pre_processing_news_data方法")
    data = []
    for s in data_list:
        item = str(s).replace("\n", "").split("_!_")
        data.append(item)
    return data
