from fastapi import APIRouter
from starlette.responses import FileResponse

router = APIRouter()


@router.get('/task/{filename}')
async def get_sask_res(filename):
    filepath = "/Users/dailinfeng/Desktop/dataops/result"
    file = f"{filepath}/{filename}"
    return FileResponse(file, filename=filename)



@router.get('/task2/{filename}')
async def get_sask_res(filename):
    filepath = "/Users/dailinfeng/Desktop/dataops/result"
    file = f"{filepath}/{filename}"
    return FileResponse(file, filename=filename)