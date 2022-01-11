package com.cuit.file_manage.operation.intf;

import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng 分发
 * @Description TODO
 * @Date 2022/1/9 2:42 PM
 * @Version 1.0
 */
@Service
public interface OperationDispatch {
    /**
     * 调度器的调度方法
     * @param operationQueue 操作队列
     * @return 调度是否成功
     */
    boolean dispatchOperation(OperationQueue operationQueue);


}
