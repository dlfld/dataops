package com.cuit.file_manage.operation.impl;

import com.cuit.common.model.base.file_manage.bo.OperationBo;
import com.cuit.file_manage.operation.intf.OperationDispatch;
import com.cuit.file_manage.operation.intf.OperationParser;
import com.cuit.common.model.base.file_manage.bo.OperationQueue;
import com.cuit.file_manage.operation.intf.OperationQueueRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Queue;

/**
 * @Author dailinfeng
 * @Description 操作队列的执行器
 * @Date 2022/1/10 8:31 PM
 * @Version 1.0
 */
@Component
public class OperationQueueRunnerImpl implements OperationQueueRunner {

    @Resource
    OperationDispatch operationDispatch;

    @Resource
    OperationParser operationParser;
    /**
     * 操作队列的执行器，在Service中调用当前方法传入操作队列
     * 该方法调用调度器和解释器进行操作
     *
     * @param operationQueue 操作队列0
     * @return 操作队列是否执行成功
     */
    @Override
    public boolean runOperationQueue(OperationQueue operationQueue,String userContact) {
        Queue<OperationBo> operationBos = operationParser.parserOperation(operationQueue);
        return operationDispatch.dispatchOperation(operationBos,userContact);

    }
}
