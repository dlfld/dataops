package com.cuit.api.file_manage;

import com.cuit.common.model.base.file_manage.vo.FileShareInfoVo;
import com.cuit.common.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:17 上午
 * @Version 1.0
 */
@Api(tags = "文件信息")
public interface FileInfoApi {
    /**
     * 获取文件树信息
     *
     * @param request request 对象
     * @return 文件树信息
     */
    @ApiOperation("获取文件树信息")
    ResponseData getFileTreeInfo(HttpServletRequest request);


    /**
     * 分享文件
     *
     * @param fileLoadInfoVo 分享的文件信息
     * @return 分享结果
     */
    @ApiOperation("分享文件")
    ResponseData shareFile(FileShareInfoVo fileLoadInfoVo);


}
