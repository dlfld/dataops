
from loguru import logger

from aop.data_func import Options
from utils.router_utils import get_router
router = get_router()


@router.get('/options/list', summary="获取所有的option列表")
async def get_options():
    # res = list([
    #     {
    #         "optUrl": "/add",
    #         "optName": "加法",
    #         "optDesc": "执行加法操作"
    #     },
    #     {
    #         "optUrl": "/min",
    #         "optName": "减法",
    #         "optDesc": "执行减法操作"
    #     },
    #     {
    #         "optUrl": "/rid",
    #         "optName": "乘法",
    #         "optDesc": "执行乘法操作"
    #     },
    #     {
    #         "optUrl": "/div",
    #         "optName": "除法",
    #         "optDesc": "执行除法操作"
    #     },
    #     {
    #         "optUrl": "/down",
    #         "optName": "梯度下降",
    #         "optDesc": "梯度下降"
    #     },
    # ])
    res = Options.options
    return res
