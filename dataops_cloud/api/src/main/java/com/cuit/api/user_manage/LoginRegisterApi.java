package com.cuit.api.user_manage;

import com.cuit.common.pojo.base.User;
import com.cuit.common.pojo.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/8 5:02 下午
 * @Version 1.0
 */
@Api(tags = "用户登录注册的接口")
public interface LoginRegisterApi {
    /**
     * 执行用户登录逻辑
     *
     * @param user 用户实体类
     * @return
     */
    @ApiOperation("用户登录")
    ResponseData userLogin(User user);
}
