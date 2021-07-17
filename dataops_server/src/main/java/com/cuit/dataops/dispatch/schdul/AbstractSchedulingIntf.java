package com.cuit.dataops.dispatch.schdul;

import com.cuit.dataops.pojo.bo.Task;

/**
 * @author dailinfeng
 */
public abstract class AbstractSchedulingIntf {
    /**
     * 开始调度
     */
    abstract void startDispatch();

    /**
     * 保存计算结果
     *
     * @param task 计算结果
     * @return 返回文件名
     */
    abstract String saveTask(Task task);

    /**
     * 通知用户计算完成
     *
     * @param contact 用户联系方式
     * @param content 通知内容
     * @return 返回通知是否成功
     */
    abstract boolean notifyUser(String contact, String content);
}
