package com.cuit.common.rpc.hutool_http;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cuit.common.pojo.Option;
import com.cuit.common.pojo.bo.ParamsBody2;
import com.cuit.common.pojo.notify.Message;
import com.cuit.common.rpc.RpcIntf;
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
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("params", paramsBody2);
        String url = baseUrl + funcUrl;
        System.out.println(url);
        String res = HttpUtil.post(url, paramMap);
        ParamsBody2 paramsBody21 = JSONUtil.toBean(res, ParamsBody2.class);
        System.out.println(paramsBody21);
        return paramsBody21;
    }

    /**
     * 远程调用实现，获取操作列表
     *
     * @return
     */
    @Override
    public List<Option> getOptions() {
        String res = HttpUtil.get(baseUrl + "/options/list");
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
     * @param message
     * @return
     */
    @Override
    public boolean nodityUser(Message message) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", message.getUser_id());
        paramMap.put("message", message.getMessage());
        HttpUtil.post(notifyUrl, paramMap);
        return true;
    }
}
