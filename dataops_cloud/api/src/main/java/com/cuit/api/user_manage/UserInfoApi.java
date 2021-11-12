package com.cuit.api.user_manage;

import com.cuit.common.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author wcw
 * @Description TODO
 * @Date 2021/11/12
 * @Version 1.0
 */
@Api("获取用户信息接口")
public interface UserInfoApi {

    @ApiOperation("获取所有用户信息")
    ResponseData getUserList();

}
