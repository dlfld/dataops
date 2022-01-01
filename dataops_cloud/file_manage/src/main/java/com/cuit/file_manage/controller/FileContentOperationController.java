package com.cuit.file_manage.controller;

import com.cuit.api.file_manage.FileContentOperationApi;
import com.cuit.common.model.base.file_manage.operation.Operation;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.RequestUtils;
import com.cuit.file_manage.service.intf.FileContentOperationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author dailinfeng
 * @Description 对文件内容进行修改的controller
 * @Date 2021/12/28 1:15 PM
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/content")
public class FileContentOperationController implements FileContentOperationApi {
    @Value(value = "${fileSystem.filePath}")
    String filePathPrefix;
    @Resource
    FileContentOperationService fileContentOperationService;

    /**
     * 对文件内容进行操作，
     *
     * @param operation 操作描述
     * @return 添加操作队列结果
     */
    @PostMapping("/operation")
    @Override
    public ResponseData contentUpdate(@RequestBody Operation operation, HttpServletRequest request) {
        //添加上前置路径
//        operation.setFilePath(FileUtil.addFilePathSuffix(operation.getFilePath(), RequestUtils.getUserName(request), filePathPrefix));
        return fileContentOperationService.addOperation(operation);
    }
}