from loguru import logger

from aop.data_func import Options
from utils.ResponseDataUtil import Result
from utils.router_utils import get_router

router = get_router()


@router.get('/options/list', summary="获取所有的option列表")
async def get_options():
    res = Options.options
    return res


@router.get('/options/list_v2', summary="获取所有的option列表V2")
async def get_options_v2():
    res = Options.options
    return Result.buildSuccess_d(res)
