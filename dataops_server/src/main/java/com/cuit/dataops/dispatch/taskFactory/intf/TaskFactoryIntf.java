package com.cuit.dataops.dispatch.taskFactory.intf;

import com.cuit.dataops.pojo.bo.Task;

/**
 * 操作task队列的规范
 *
 */
public interface TaskFactoryIntf {
    //向队列中添加task
    //添加成功返回true 添加失败返回false
    boolean offer(Task task);

    //获取队列顶端元素  不删除
    Task peek();

    // 队列顶端元素出队
    Task poll();

    // task队列是否为空的判断
    boolean isEmpty();
}
