package com.cuit.dataops.rpc;

import com.cuit.dataops.pojo.Option;
import com.cuit.dataops.pojo.bo.ParamsBody;
import com.dtflys.forest.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 远程调用包
 */
@Component
@BaseRequest(
        baseURL = "${baseUrl}"   // 默认域名
)
public interface Pyservice {
    /**
     * 远程调用python
     * @param funcUrl 调用python服务的url
     * @param params  参数
     * @return  返回的是一个map数组
     */
    @Request(url = "${funcUrl}",type = "post")
   String callFunction(@Var("funcUrl") String funcUrl, @JSONBody ParamsBody params);

    @Request("${funcUrl}")
    String callFunctionDemo(@Var("funcUrl") String funcUrl);
    /**
     * 获取python端options列表
     * @return
     */
    @Request(url = "/options/list", type = "get")
    List<Option> getOptions();
}
