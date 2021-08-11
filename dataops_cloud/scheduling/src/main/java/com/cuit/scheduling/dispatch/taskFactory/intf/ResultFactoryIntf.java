package com.cuit.scheduling.dispatch.taskFactory.intf;

import com.cuit.common.pojo.base.Task;
import org.springframework.stereotype.Service;

/**
 * task执行之后的结果队列
 * @author dailinfeng
 */
@Service
public interface ResultFactoryIntf {


    /**
     * 保存计算结果
     *
     * @param task 计算结果
     * @return 返回文件名
     */
     String saveTask(Task task);

    /**
     * 通知用户计算完成
     * content是结果下载的url  传进来的content只是存的结果的文件名
     *
     * @param contact 用户联系方式
     * @param content 通知内容   目前这个版本是url的方式
     * @return 返回通知是否成功
     */
    boolean notifyUser(String contact,String content);

}
