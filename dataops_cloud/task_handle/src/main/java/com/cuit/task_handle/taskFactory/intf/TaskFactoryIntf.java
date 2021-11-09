package com.cuit.task_handle.taskFactory.intf;

import com.cuit.common.model.base.Task;

/**
 * 操作task队列的规范
 *
 * @author dailinfeng
 */
public interface TaskFactoryIntf {
    /**
     * 向队列中添加task
     * 添加成功返回true 添加失败返回false
     * @param task 任务
     * @return 添加是否为空
     */
    boolean offer(Task task);

}
