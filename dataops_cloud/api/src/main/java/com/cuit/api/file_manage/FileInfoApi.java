package com.cuit.api.file_manage;

import com.cuit.common.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:17 上午
 * @Version 1.0
 */
@Api(tags = "文件信息")
public interface FileInfoApi {
    @ApiOperation("获取文件树信息")
    ResponseData getFileTreeInfo();


}
