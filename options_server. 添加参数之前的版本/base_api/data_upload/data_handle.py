from fastapi import UploadFile, File

from utils.ResponseDataUtil import Result
from utils.config_parse_util import get_config
from utils.file_utils import judge_file_path
from utils.router_utils import get_router

import uuid

router = get_router()
from loguru import logger

'''
上传数据文件，返回前端上传文件的具体保存地址
'''


@router.post('/data_upload', summary="上传数据文件")
async def data_upload(file: UploadFile = File(...)):
    try:
        res = await file.read()
        file_path = get_config("data_upload", "data_save_path")
        # 判断当前路径是否存在，如果不存在就创建
        judge_file_path(file_path)
        file_name = f"{uuid.uuid1()}.{file.filename.split('.')[1]}"
        file_full_path = f"{file_path}/{file_name}"
        with open(file_full_path, "wb") as f:
            f.write(res)
    except Exception as e:
        Result.buildError_d(e)
        logger.info(e)
    return Result.buildSuccess_d(file_full_path)
