package com.cuit.api.file_manage;

import com.cuit.common.model.base.file_manage.operation.Operation;
import com.cuit.common.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:16 PM
 * @Version 1.0
 */
@Api(tags = "文件内容修改")
public interface FileContentOperationApi {

    /**
     * 对文件内容进行操作，
     * @param operation 操作描述
     * @return 添加操作队列结果
     */
    @ApiOperation("文件内容操作")
    ResponseData contentUpdate(Operation operation, HttpServletRequest request);
}
