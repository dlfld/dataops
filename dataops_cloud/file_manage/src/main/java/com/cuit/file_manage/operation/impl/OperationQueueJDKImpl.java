package com.cuit.file_manage.operation.impl;

import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.file_manage.operation.intf.OperationQueue;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:52 PM
 * @Version 1.0
 */
@Component
public class OperationQueueJDKImpl implements Serializable, OperationQueue {
    private static final long serialVersionUID = -4533858678849721095L;
    /**
     * 操作队列
     */
    Deque<Operation> operationDeque = new LinkedList<>();


    /**
     * 向队尾添加一个元素
     *
     * @param operation 操作对象
     * @return 是否完成
     */
    @Override
    public boolean offer(Operation operation) {
        return operationDeque.offer(operation);
    }

    /**
     * 返回队首元素并删除
     *
     * @return 操作元素
     */
    @Override
    public Operation poll() {
        return operationDeque.poll();
    }

    /**
     * 返回第一个元素
     *
     * @return 操作元素
     */
    @Override
    public Operation peek() {
        return operationDeque.peek();
    }

    /**
     * 删除队列尾部的元素   用于撤回操作
     *
     * @return
     */
    @Override
    public boolean pop() {
        operationDeque.pop();
        return false;
    }

}
