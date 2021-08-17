
from starlette.responses import FileResponse
from utils.config_parse_util import get_config
from utils.router_utils import get_router

router = get_router()


@router.get('/data_download/{file_name}', summary="下载数据文件")
async def data_download(file_name):
    """
    根据传上来的文件名下载文件
    :param file_name: 文件名
    :return:
    """
    file_path = get_config("data_upload", "data_save_path")
    return FileResponse(f'{file_path}/{file_name}', filename=file_name)
