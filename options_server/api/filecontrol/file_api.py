from starlette.responses import FileResponse

# from aop.data_func import func_config
from utils.router_utils import get_router

router = get_router()


# @func_config(dict({"url": "/add", "desc": "执行加法操作", "name": "加法"}))
@router.get('/task/{filename}')
async def get_sask_res(filename):
    filepath = "/Users/dailinfeng/Desktop/dataops/result"
    file = f"{filepath}/{filename}"
    return FileResponse(file, filename=filename)


# @func_config(dict({"url": "/add", "desc": "执行加法操作", "name": "加法"}))
@router.get('/task2/{filename}')
async def get_sask_res(filename):
    filepath = "/Users/dailinfeng/Desktop/dataops/result"
    file = f"{filepath}/{filename}"
    return FileResponse(file, filename=filename)
