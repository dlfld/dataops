package com.cuit.file_manage.service.impl;

import com.cuit.common.model.base.file_manage.operation.Operation;
import com.cuit.common.model.response.ResponseData;
import com.cuit.file_manage.service.intf.FileContentOperationService;
import org.springframework.stereotype.Component;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:38 PM
 * @Version 1.0
 */
@Component
public class FileContentOperationServiceImpl implements FileContentOperationService {
    /**
     * 前端传过来一个操作，将这个操作存储到操作队列里面去
     * 如果有操作队列就存到操作队列，如果没有的话就创建一个操作队列
     * 操作队列存储在和所操作对象同一级的目录当中。是一个meta文件
     *
     * @param operation
     * @return
     */
    @Override
    public ResponseData addOperation(Operation operation) {
        return null;
    }
}
