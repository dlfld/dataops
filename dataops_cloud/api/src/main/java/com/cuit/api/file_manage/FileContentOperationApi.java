package com.cuit.api.file_manage;

import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.common.model.base.file_manage.vo.OperationVo;
import com.cuit.common.model.base.file_manage.vo.StartOperationVo;
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
     * 对文件内容进行操作，将该操作加入操作队列中，然后返回该操作的ID
     *
     * @param operationVo 操作描述
     * @return 添加操作队列结果
     */
    @ApiOperation("文件内容操作,返回的是当前操作对应的ID")
    ResponseData contentUpdate(OperationVo operationVo, HttpServletRequest request);


    /**
     * 执行操作的撤回操作，传过来的是当前操作文件的路径
     *
     * @param filePath 当前操作文件的路径
     * @return 返回操作撤回是否成功
     */
    @ApiOperation("撤回操作")
    ResponseData recallOperation(String filePath);

    /**
     * 执行操作队列的执行操作
     * @param startOperationVo 提交操作的相关信息
     * @return
     */
    @ApiOperation("提交，开始执行所有操作")
    ResponseData startOperation(StartOperationVo startOperationVo);
}
