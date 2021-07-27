package com.cuit.common.rpc;

import com.cuit.common.pojo.Option;
import com.cuit.common.pojo.bo.ParamsBody;
import com.cuit.common.pojo.bo.ParamsBody2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 远程调度的实现
 * 目前是以http的方式调度，今后会以其他的方式调度，所以调度抽取一层出来
 * @author dailinfeng
 */
@Slf4j
@Component
public class RpcImpl {
    @Resource
    Pyservice pyservice ;
//    static Logger logger = LoggerFactory.getLogger(RpcImpl.class);
    /**
     * 以http的方式对python实现的算法进行调度
     *  废弃 ⚠️
     * @param funcUrl
     * @param params
     * @return
     */
    public Object httpRpc(String funcUrl, ParamsBody params) {
//        log.info(params.toString());
        return pyservice.callFunction(funcUrl, params);
    }

    /**
     * 第二版本的调度实现
     * 封装参数为list的对象 添加了参数实体的信息和版本号
     * 后期可以根据版本号来更新参数
     * @param funcUrl
     * @param paramsBody2
     * @return
     */
    public ParamsBody2 httpRpcV2(String funcUrl, ParamsBody2 paramsBody2) {
//        log.info(paramsBody2.toString());

        return pyservice.callFunctionV2(funcUrl, paramsBody2);
    }

    /**
     * 远程调用实现，获取操作列表
     * @return
     */
    public List<Option> getOptions(){
        return pyservice.getOptions();
    }

}
