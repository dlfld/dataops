package com.cuit.task_handle.taskFactory.impl;

import com.cuit.common.pojo.bo.Task;
import com.cuit.task_handle.taskFactory.intf.TaskFactoryIntf;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 当前的task队列使用的是静态变量的方式
 * @author dailinfeng
 */
@Data
@Component
public class TaskFactoryStaticImpl implements TaskFactoryIntf {
    private static Queue<Task> taskQueue = new LinkedList<>();

    @Override
    public boolean offer(Task task) {
        return taskQueue.offer(task);
    }

    @Override
    public Task peek() {
        return taskQueue.peek();
    }

    @Override
    public Task poll() {
        return taskQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}
