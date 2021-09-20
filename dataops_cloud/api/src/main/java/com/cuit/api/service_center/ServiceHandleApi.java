package com.cuit.api.service_center;

import com.cuit.common.pojo.base.PyClient;
import com.cuit.common.pojo.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/9/20 11:28 上午
 * @Version 1.0
 */
@Api(tags = "python服务中心-注册与发现接口")
public interface ServiceHandleApi {
    @ApiOperation("服务主动上报到注册中心进行注册")
    ResponseData registerService(PyClient pyClient);

}
