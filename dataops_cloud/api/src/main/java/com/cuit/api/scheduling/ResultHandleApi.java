package com.cuit.api.scheduling;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/29 8:31 下午
 * @Version 1.0
 */
@Api(tags = "结果文件下载")
public interface ResultHandleApi {

    @ApiOperation("下载计算结果")
    void downloadResultFile(String fileName, HttpServletResponse httpServletResponse);


}
