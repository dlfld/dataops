package com.cuit.user_manage.controller;


import com.cuit.api.user_manage.UserInfoApi;
import com.cuit.common.model.response.ResponseData;
import com.cuit.user_manage.service.intf.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @Author wcw
 * @Description TODO
 * @Date 2021/11/12
 * @Version 1.0
 */
public class UserInfoController implements UserInfoApi {

    @Resource
    UserInfoService userInfoService;

    /**
     * 获取用户信息列表
     * @return 用户信息列表
     */
    @Override
    @GetMapping("/userList")
    public ResponseData getUserList() {
        return userInfoService.getUserList();
    }
}
