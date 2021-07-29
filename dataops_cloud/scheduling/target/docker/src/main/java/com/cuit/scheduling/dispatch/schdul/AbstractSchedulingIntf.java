package com.cuit.scheduling.dispatch.schdul;

import com.cuit.common.pojo.bo.Task;

/**
 * 调度中心的方法接口
 * @author dailinfeng
 */
public abstract class AbstractSchedulingIntf {
    /**
     * 开始调度
     */
    abstract void startDispatch(Task task);
}
