//package com.cuit.common.rpc.forest_http;
//
//import com.cuit.common.pojo.base.Option;
//import com.cuit.common.pojo.bo.ParamsBody2;
//import com.cuit.common.pojo.notify.Message;
//import com.cuit.common.rpc.RpcIntf;
////import com.cuit.common.rpc.forest_http.notify.QQBotRpc;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 调用具体的实现，forest是需要写接口的 因此用这个类来调用接口，以及以后的是否请求通的逻辑判断
// * @Author dailinfeng
// * @Description TODO
// * @Date 2021/7/28 10:51 上午
// * @Version 1.0
// */
////@Component
//public class ForestImpl implements RpcIntf {
//    @Resource
//    Pyservice pyservice ;
//
//    @Resource
//    QQBotRpc qqBotRpc;
//
//    /**
//     * 第二版本的调度实现
//     * 封装参数为list的对象 添加了参数实体的信息和版本号
//     * 后期可以根据版本号来更新参数
//     * @param funcUrl
//     * @param paramsBody2
//     * @return
//     */
//    @Override
//    public ParamsBody2 httpRpcV2(String funcUrl, ParamsBody2 paramsBody2) {
//        return pyservice.callFunctionV2(funcUrl, paramsBody2);
//    }
//
//    /**
//     * 远程调用实现，获取操作列表
//     * @return
//     */
//    @Override
//    public List<Option> getOptions(){
//        return pyservice.getOptions();
//    }
//
//    /**
//     *通知用户结果运行成功
//     * @param message
//     * @return
//     */
//    @Override
//    public boolean nodityUser(Message message){
//        qqBotRpc.nodityUser(message);
//        return true;
//    }
//}
