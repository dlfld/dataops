package com.cuit.user_manage.controller;

import com.cuit.api.user_manage.LoginRegisterApi;
import com.cuit.common.model.base.user_manage.User;
import com.cuit.common.model.base.user_manage.vo.UserLoginVo;
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
     *
     * @param user 用户实体类
     * @return
     */
    @Override
    @PostMapping("/login")
    public ResponseData userLogin(@RequestBody UserLoginVo user) {
        return loginRegisterService.userLogin(user);
    }


    /**
     * 用户注册逻辑
     * @param user 用户实体类
     * @return 注册成功标注
     */
    @Override
    @PostMapping("/register")
    public ResponseData userRegister(@RequestBody User user) {
        return loginRegisterService.userRegister(user);
    }


    @Override
    @GetMapping("/userList")
    public ResponseData getUserList() {
        return loginRegisterService.getUserList();
    }
}
