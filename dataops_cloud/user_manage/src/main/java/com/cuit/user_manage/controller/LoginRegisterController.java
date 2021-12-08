package com.cuit.user_manage.controller;

import com.cuit.api.user_manage.LoginRegisterApi;
import com.cuit.common.model.base.user_manage.vo.UserLoginVo;
import com.cuit.common.model.base.user_manage.vo.UserRegisterVo;
import com.cuit.common.model.response.ResponseData;
import com.cuit.user_manage.service.intf.LoginRegisterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/8 6:37 下午
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/sso")
public class LoginRegisterController implements LoginRegisterApi {
    @Resource
    LoginRegisterService loginRegisterService;

    /**
     * 用户登录
     * @param userLoginVo
     * @return
     */
    @Override
    @PostMapping("/login")
    public ResponseData userLogin(@RequestBody UserLoginVo userLoginVo) {
        return loginRegisterService.userLogin(userLoginVo);
    }


    /**
     * 用户注册逻辑
     * @param user
     * @return 注册成功标注
     */
    @Override
    @PostMapping("/register")
    public ResponseData userRegister(@RequestBody UserRegisterVo user) {
        return loginRegisterService.userRegister(user);
    }

}
