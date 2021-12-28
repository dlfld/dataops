package com.cuit.file_manage.service.intf;

import com.cuit.common.model.base.file_manage.operation.Operation;
import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:37 PM
 * @Version 1.0
 */
@Service
public interface FileContentOperationService {

    /**
     * 前端传过来一个操作，将这个操作存储到操作队列里面去
     * 如果有操作队列就存到操作队列，如果没有的话就创建一个操作队列
     * 操作队列存储在和所操作对象同一级的目录当中。是一个meta文件
     * @param operation
     * @return
     */
    ResponseData addOperation(Operation operation);
}
