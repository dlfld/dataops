package com.cuit.dataops.api;

import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(tags = "Options 相关接口")
public interface OptionsApi {
    /**
     * 获取options列表
     * （获取所有方法列表）
     * @return
     */
    @ApiOperation("获取所有options方法")
    ResponseData getOptionsList();
    @ApiOperation("提交操作结果")
    ResponseData submitOptions(SubmitOptionsRequest submitOptionsRequest);

    @ApiOperation("提交操作结果2")
    ResponseData submitOptions2(List<Node> nodes);
}
