from fastapi import APIRouter
from loguru import logger

router = APIRouter()


@router.get('/options/list')
async def get_options():
    res = list([
        {
            "optUrl": "/add",
            "optName": "加法",
            "optDesc": "执行加法操作"
        },
        {
            "optUrl": "/min",
            "optName": "减法",
            "optDesc": "执行减法操作"
        },
        {
            "optUrl": "/rid",
            "optName": "乘法",
            "optDesc": "执行乘法操作"
        },
        {
            "optUrl": "/div",
            "optName": "除法",
            "optDesc": "执行除法操作"
        },
        {
            "optUrl": "/down",
            "optName": "梯度下降",
            "optDesc": "梯度下降"
        },
    ])
    return res
