//package com.cuit.common.rpc.forest_http;
//
//import com.cuit.common.pojo.Option;
//import com.cuit.common.pojo.bo.ParamsBody;
//import com.cuit.common.pojo.bo.ParamsBody2;
//import com.dtflys.forest.annotation.BaseRequest;
//import com.dtflys.forest.annotation.JSONBody;
//import com.dtflys.forest.annotation.Request;
//import com.dtflys.forest.annotation.Var;
//
//import java.util.List;
//
///**
// * 远程调用包
// * @author dailinfeng
// */
////@Component
//@BaseRequest(
//        baseURL = "${baseUrl}"   // 默认域名
//)
//public interface Pyservice {
//    /**
//     * 远程调用python
//     *废弃 ⚠️
//     * @param funcUrl 调用python服务的url
//     * @param params  参数
//     * @return 返回的是一个map数组
//     */
//    @Request(url = "${funcUrl}", type = "post")
//    String callFunction(@Var("funcUrl") String funcUrl, @JSONBody ParamsBody params);
//
//    /**
//     * 远程调用python服务版本2
//     * @param funcUrl 服务的url
//     * @param params 参数
//     * @return 返回的是经过处理的参数
//     */
//    @Request(url = "${funcUrl}", type = "post")
//    ParamsBody2 callFunctionV2(@Var("funcUrl") String funcUrl, @JSONBody ParamsBody2 params);
//
//    /**
//     * demo调用
//     * @param funcUrl url
//     * @return  返回值
//     */
//    @Request("${funcUrl}")
//    String callFunctionDemo(@Var("funcUrl") String funcUrl);
//
//    /**
//     * 获取python端options列表
//     *
//     * @return 返回所有操作的列表
//     */
//    @Request(url = "/options/list", type = "get")
//    List<Option> getOptions();
//}
