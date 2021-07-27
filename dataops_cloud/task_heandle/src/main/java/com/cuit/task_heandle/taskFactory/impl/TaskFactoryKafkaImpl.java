package com.cuit.task_heandle.taskFactory.impl;

import com.cuit.common.pojo.bo.Task;
import com.cuit.task_heandle.taskFactory.intf.TaskFactoryIntf;

/**
 * kafka的task队列实现
 *
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/27 8:46 上午
 * @Version 1.0
 */
public class TaskFactoryKafkaImpl implements TaskFactoryIntf {
    /**
     * 向队列中添加task
     * 添加成功返回true 添加失败返回false
     *
     * @param task 任务
     * @return 添加是否为空
     */
    @Override
    public boolean offer(Task task) {
        return false;
    }

    /**
     * 获取队列顶端元素  不删除
     *
     * @return 返回队列顶端元素
     */
    @Override
    public Task peek() {
        return null;
    }

    /**
     * 队列顶端元素出队
     *
     * @return 返回队列顶端元素 并删除队列顶端元素
     */
    @Override
    public Task poll() {
        return null;
    }

    /**
     * task队列是否为空的判断
     *
     * @return 返回是否为空
     */
    @Override
    public boolean isEmpty() {
        return false;
    }
}
