package com.cuit.file_manage.controller;

import com.cuit.api.file_manage.FileContentOperationApi;
import com.cuit.common.model.base.file_manage.operation.Operation;
import com.cuit.common.model.response.ResponseData;
import com.cuit.file_manage.service.intf.FileContentOperationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    FileContentOperationService fileContentOperationService;
    /**
     * 对文件内容进行操作，
     * @param operation 操作描述
     * @return 添加操作队列结果
     */
    @PostMapping("/operation")
    @Override
    public ResponseData contentUpdate(Operation operation) {
        return null;
    }
}
