package com.cuit.dataops.dispatch.taskFactory.impl;

import com.cuit.dataops.dispatch.taskFactory.intf.ResultFactoryIntf;
import com.cuit.dataops.pojo.bo.Task;
import org.springframework.stereotype.Component;

@Component
public class ResultImpl implements ResultFactoryIntf {


    /**
     * 保存task
     *
     * 当前的保存是 以task的id为文件名保存到json文件内 取出的时候以文件名拼接为url取出结果
     * @param task 任务
     * @return 保存之后返回的连接
     */
    @Override
    public String save(Task task) {

        return null;
    }

    /**
     * 提醒用户操作完成
     *当前的实现方式是发送qq消息的方式
     * @param contact 联系方式
     * @param content url内容
     */
    @Override
    public void notifyComplete(String contact, String content) {

    }
}
