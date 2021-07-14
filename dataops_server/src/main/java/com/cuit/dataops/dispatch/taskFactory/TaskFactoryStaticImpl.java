package com.cuit.dataops.dispatch.taskFactory;

import com.cuit.dataops.pojo.bo.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 当前的task队列使用的是静态变量的方式
 */
@Data
//@NoArgsConstructor
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
