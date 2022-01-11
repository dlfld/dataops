package com.cuit.file_manage.operation.impl;

import com.cuit.file_manage.operation.intf.OperationDispatch;
import com.cuit.file_manage.operation.intf.OperationQueue;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng
 * @Description TODO 解析器
 * @Date 2022/1/9 2:43 PM
 * @Version 1.0
 */
@Service
public class OperationDispatchImpl implements OperationDispatch {
    /**
     * 调度器的调度方法
     *
     * @param operationQueue 操作队列
     * @return 调度是否成功
     */
    @Override
    public boolean dispatchOperation(OperationQueue operationQueue) {
        return false;
    }
}
