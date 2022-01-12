package com.cuit.file_manage.operation.intf;

import com.cuit.common.model.base.file_manage.bo.OperationQueue;
import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng
 * @Description 操作队列执行器
 * @Date 2022/1/10 8:30 PM
 * @Version 1.0
 */
@Service
public interface OperationQueueRunner {
    /**
     * 操作队列的执行器，在Service中调用当前方法传入操作队列
     * 该方法调用调度器和解释器进行操作
     * @param operationQueue 操作队列0
     * @return 操作队列是否执行成功
     */
    boolean runOperationQueue(OperationQueue operationQueue);
}
