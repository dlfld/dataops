package com.cuit.dataops.dispatch.taskFactory.intf;

import com.cuit.dataops.pojo.bo.Task;

/**
 * task执行之后的结果队列
 * @author dailinfeng
 */
public interface ResultFactoryIntf {

    /**
     * 保存task
     * @param task 任务
     * @return 保存之后返回的连接
     */
     String save(Task task);

    /**
     * 提醒用户操作完成
     * @param contact 联系方式
     * @param content url内容
     */
     void notifyComplete(String contact,String content);

}
