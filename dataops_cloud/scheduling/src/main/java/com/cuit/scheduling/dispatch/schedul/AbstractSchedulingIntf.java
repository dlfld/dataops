package com.cuit.scheduling.dispatch.schedul;

import com.cuit.common.model.base.ops.Task;

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
