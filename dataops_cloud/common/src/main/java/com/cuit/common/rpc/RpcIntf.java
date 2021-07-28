package com.cuit.common.rpc;

import com.cuit.common.pojo.Option;
import com.cuit.common.pojo.bo.ParamsBody2;
import com.cuit.common.pojo.notify.Message;

import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/28 3:58 下午
 * @Version 1.0
 */
public interface RpcIntf {

    public ParamsBody2 httpRpcV2(String funcUrl, ParamsBody2 paramsBody2);

    /**
     * 远程调用实现，获取操作列表
     *
     * @return
     */
    public List<Option> getOptions();

    /**
     * 远程调用，QQ机器人进行通知
     * @param message
     * @return
     */
    public boolean nodityUser(Message message);
}
