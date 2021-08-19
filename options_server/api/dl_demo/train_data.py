import numpy as np
import torch
from pydantic import BaseModel

from aop.data_func import func_config
from api.dl_demo.train import train
from utils.config_parse_util import get_config
from utils.router_utils import get_router

router = get_router()


class Params(BaseModel):
    items: list = None


def save_model(model):
    """
    保存模型的方法
    :param model:
    :return:
    """
    file_path = f'{get_config("data_upload", "data_save_path")}/train_diabetes.pkl'
    torch.save(model, file_path)
    return {
        "file_full_path": file_path,  # 文件的全路径，包括文件名 eg：/dataops/data/a04a77afb98a.csv
        "file_name": "train_diabetes.pkl"
    }


@func_config(
    data=dict({
        "optUrl": "/train_diabetes",
        # 功能模块的调用url  有个想法：这个url现在是人工配置的，实际上根本不需要人工配置，这个url对使用是无感的，只是为了调用，因此能否自动生成（前提是永远不会重复）
        "optDesc": "读取出diabetes.csv中的数据,并进行训练，最后保存模型",  # 模块功能简介
        "optName": "训练diabetes的模型",  # 展示在前端的模块名
        "desc": "train_diabetes",  # 需要的数据的desc
        "changeVersion": False,  # 是否修改版本号
        "return_desc": "train_diabetes_pkl"  # 经过处理之后的desc
    }),
    read_file_func=lambda x: np.loadtxt(x, delimiter=',', dtype=np.float32),  # 读取文件的方法
    save_file_func=save_model,  # 写入文件的方法
    # pre_handle_adapter=lambda x: np.array(x),  # 在进入计算之前需要执行的数据格式转换操作
    # after_handle_adapter=lambda x: pd.DataFrame(x)  # 在计算完成之后需要进行的数据格式转换操作
)
def train_diabetes(data_frame):
    """
    读取数据进行训练并保存模型
    :param data_frame:
    :return:
    """
    model = train(data_frame)
    return model
