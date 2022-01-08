package com.cuit.file_manage.operation.intf;

import com.cuit.common.model.base.file_manage.Operation;

/**
 * @Author dailinfeng
 * @Description 操作队列的抽象类
 * @Date 2021/12/28 3:22 PM
 * @Version 1.0
 */
public interface OperationQueue {

    /**
     * 向队尾添加一个元素
     * @param operation 操作对象
     * @return 是否完成
     */
    boolean offer(Operation operation);

    /**
     * 返回队首元素并删除
     * @return 操作元素
     */
    Operation poll();

    /**
     * 返回第一个元素
     * @return 操作元素
     */
    Operation peek();

    /**
     *  删除队列尾部的元素   用于撤回操作
     * @return
     */
    boolean pop();

}
