package com.cuit.common.rpc;

import com.cuit.common.pojo.base.Option;
import com.cuit.common.pojo.base.ParamsBody2;
import com.cuit.common.pojo.notify.Message;
import com.cuit.common.rpc.hutool_http.HutoolImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 远程调度的实现
 * 转为springcloud之后原来的forest出问题了  具体原因未知
 * 因此引入hutool的http工具进行http的远程调用 后面可能会换，为了换http调用框架的时候不改动上层代码
 * 因此引入RpcImpl  上层代码调用RpcImpl  RpcImpl来决定调用那个框架
 *RpcIntf 定义有那些方法
 * @author dailinfeng
 */
@Component
public class RpcImpl implements RpcIntf{
//    @Resource
//    ForestImpl forest;
    @Resource
    HutoolImpl hutool;

    /**
     * 第二版本的调度实现
     * 封装参数为list的对象 添加了参数实体的信息和版本号
     * 后期可以根据版本号来更新参数
     *
     * @param funcUrl
     * @param paramsBody2
     * @return
     */

    @Override
    public ParamsBody2 httpRpcV2(String funcUrl, ParamsBody2 paramsBody2) {
//        return forest.httpRpcV2(funcUrl, paramsBody2);
        return hutool.httpRpcV2(funcUrl, paramsBody2);
    }

    /**
     * 远程调用实现，获取操作列表
     *
     * @return
     */
    @Override
    public List<Option> getOptions() {
//        return forest.getOptions();
        return hutool.getOptions();
    }

    /**
     * 远程调用，QQ机器人进行通知
     * @param message
     * @return
     */
    @Override
    public boolean notifyUser(Message message){
        return hutool.notifyUser(message);
    }
}
