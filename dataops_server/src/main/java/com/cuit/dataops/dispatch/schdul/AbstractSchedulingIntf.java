package com.cuit.dataops.dispatch.schdul;

import com.cuit.dataops.pojo.bo.Task;

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
