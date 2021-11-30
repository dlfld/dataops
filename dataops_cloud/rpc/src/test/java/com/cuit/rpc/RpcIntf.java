package com.cuit.rpc;

import com.cuit.common.model.base.ops.Option;
import com.cuit.common.model.base.ops.ParamsBody2;
import com.cuit.common.model.notify.Message;

import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/28 3:58 下午
 * @Version 1.0
 */
public interface RpcIntf {

    ParamsBody2 httpRpcV2(String funcUrl, ParamsBody2 paramsBody2);

    /**
     * 远程调用实现，获取操作列表
     *
     * @return
     */
    List<Option> getOptions();

    /**
     * 远程调用，QQ机器人进行通知
     *
     * @param message
     * @return
     */
    boolean notifyUser(Message message);


}
