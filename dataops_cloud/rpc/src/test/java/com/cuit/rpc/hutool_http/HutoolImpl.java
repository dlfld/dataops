package com.cuit.rpc.hutool_http;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cuit.common.pojo.base.Option;
import com.cuit.common.pojo.base.ParamsBody2;
import com.cuit.common.pojo.notify.Message;
import com.cuit.rpc.RpcIntf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/28 10:53 上午
 * @Version 1.0
 */
@Component
public class HutoolImpl implements RpcIntf {
    @Value(value = "${forest.variables.baseUrl}")
    String baseUrl;

    @Value(value = "${forest.variables.notifyUrl}")
    String notifyUrl;

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
        //不用注册中心的情况
//        String url = baseUrl + funcUrl;
//        使用注册中心的时候就不需要用baseurl了，而是直接传的全路径
        String url = funcUrl;
        String res = HttpRequest.post(url)
                .body(JSONUtil.toJsonStr(paramsBody2))
                .execute().body();
        ParamsBody2 paramsBody21 = JSONUtil.toBean(res, ParamsBody2.class);
        System.out.println(paramsBody21);
        return paramsBody21;
    }

    /**
     * 远程调用实现，获取操作列表
     * 这个方法暂时废弃了，因为现在如果使用nacos的方式的话这种方式是不可以的
     *
     * @return
     */
    @Override
    public List<Option> getOptions() {
        String res = HttpRequest.get(baseUrl + "/options/list")
                .timeout(2000)
                .execute()
                .body();
        JSONArray jsonArray = JSONUtil.parseArray(res);
        List<Option> options = new ArrayList<>();
        for (JSONObject o : jsonArray.jsonIter()) {
            Option option = JSONUtil.toBean(o, Option.class);
            options.add(option);
        }
        return options;
    }

    /**
     * 通知用户
     *
     * @param message
     * @return
     */
    @Override
    public boolean notifyUser(Message message) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", message.getUser_id());
        paramMap.put("message", message.getMessage());
        HttpUtil.post(notifyUrl + "/send_private_msg", paramMap);
        return true;
    }
}
