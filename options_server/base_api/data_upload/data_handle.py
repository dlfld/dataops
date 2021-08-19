from fastapi import UploadFile, File

from utils.response_data_util import Result
from utils.config_parse_util import get_config, ConfigGet
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
        file_path = ConfigGet.get_data_file_path()
        file_name = f"{uuid.uuid1()}.{file.filename.split('.')[1]}"
        file_full_path = f"{file_path}/{file_name}"
        with open(file_full_path, "wb") as f:
            f.write(res)
        downloadUrl = f'{ConfigGet.get_server_host()}/data_download/{file_name}'
    except Exception as e:
        Result.buildError_d(e)
        logger.info(e)
    return Result.buildSuccess_d({"filePath": file_full_path, "fileName": file_name, "downloadUrl":downloadUrl})
