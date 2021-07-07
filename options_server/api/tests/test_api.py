from fastapi import APIRouter
from loguru import logger
from pydantic import BaseModel

router = APIRouter()


class Params(BaseModel):
    items: list = None


@router.post('/add')
async def get_options(params: Params):
    return "".join([item for item in params.items])+"add "


@router.post('/min')
async def get_options(params: Params):
    return "".join([item for item in params.items])+"min "


@router.post('/rid')
async def get_options(params: Params):
    return "".join([item for item in params.items])+"rid "


@router.post('/div')
async def get_options(params: Params):
    return "".join([item for item in params.items])+"div"

@router.post('/ ')
async def get_options(params: Params):
    return ""
