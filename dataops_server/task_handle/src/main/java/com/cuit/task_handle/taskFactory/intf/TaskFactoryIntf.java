package com.cuit.task_handle.taskFactory.intf;

import com.cuit.common.pojo.bo.Task;

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

    /**
     * 获取队列顶端元素  不删除
     * @return 返回队列顶端元素
     */
    Task peek();

    /**
     * 队列顶端元素出队
     * @return  返回队列顶端元素 并删除队列顶端元素
     */
    Task poll();

    /**
     * task队列是否为空的判断
     * @return 返回是否为空
     */
    boolean isEmpty();
}
