package com.cuit.file_manage.operation.impl;

import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.file_manage.operation.intf.OperationQueue;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:52 PM
 * @Version 1.0
 */
public class OperationQueueJDKImpl implements Serializable, OperationQueue {
    private static final long serialVersionUID = -4533858678849721095L;
    /**
     * 操作队列
     */
    Queue<Operation> opeartionQueue = new LinkedList<>();


    /**
     * 向队尾添加一个元素
     *
     * @param operation 操作对象
     * @return 是否完成
     */
    @Override
    public boolean offer(Operation operation) {
        return opeartionQueue.offer(operation);
    }

    /**
     * 返回队首元素并删除
     *
     * @return 操作元素
     */
    @Override
    public Operation poll() {
        return opeartionQueue.poll();
    }

    /**
     * 返回第一个元素
     *
     * @return 操作元素
     */
    @Override
    public Operation peek() {
        return opeartionQueue.peek();
    }

}
