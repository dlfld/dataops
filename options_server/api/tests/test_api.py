from fastapi import APIRouter
from loguru import logger

router = APIRouter()


@router.get('/add/{nums}')
async def get_options(nums: int):
    return nums + 2


@router.get('/min/{nums}')
async def get_options(nums: int):
    return nums - 2


@router.get('/rid/{nums}')
async def get_options(nums: int):
    return nums * 2


@router.get('/div/{nums}')
async def get_options(nums: int):
    return nums / 2
