from starlette.responses import FileResponse

# from aop.data_func import func_config
from utils.router_utils import get_router

router = get_router()


@router.get('/task/{filename}',summary="获取执行之后的结果")
async def get_task_res(filename):
    filepath = "/Users/dailinfeng/Desktop/dataops/result"
    file = f"{filepath}/{filename}"
    return FileResponse(file, filename=filename)

