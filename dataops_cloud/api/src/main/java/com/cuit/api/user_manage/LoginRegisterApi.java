package com.cuit.api.user_manage;

import com.cuit.common.model.base.user_manage.vo.UserLoginVo;
import com.cuit.common.model.base.user_manage.vo.UserRegisterVo;
import com.cuit.common.model.response.ResponseData;
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
     * @param userLoginVo 用户实体类
     * @return
     */
    @ApiOperation("用户登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "form"),
//            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form"),
//    })
    ResponseData userLogin(UserLoginVo userLoginVo);

    /**
     * 用户注册逻辑
     *
     * @param user 用户实体类
     * @return 注册成功标注
     */
    @ApiOperation("用户注册")
    ResponseData userRegister(UserRegisterVo user);

}
