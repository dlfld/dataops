
from icecream import ic

from aop.data_func import func_config


def downloadUrl(path):
    ic(path)


@func_config(
    data=dict({
        "optUrl": "/test_download",
        "optDesc": "测试文件自动下载的组件",  # 模块功能简介
        "optName": "测试",  # 展示在前端的模块名
        "desc": "start desc",  # 需要的数据的desc  这个desc不能够删除的，并且不能够相同因为这是调度端进行数据更新的东西  但是并不是寻找数据的标志符了，后面可能用来当作模块数据接口规范
        "return_desc": "after start desc"  # 经过处理之后的desc
    }),
    pre_handle_adapter=downloadUrl
)
def handle_mul(data):
    ic("乘法")
    ic(data)
