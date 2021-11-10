package com.cuit.user_manage.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.user_manage.User;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.user_manage.service.intf.LoginRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/8 6:40 下午
 * @Version 1.0
 */
@Component
@Slf4j
public class LoginRegisterServiceImpl implements LoginRegisterService {
    /**
     * 文件路径 指的是文件树的根节点
     */
    @Value(value = "${path.home}")
    protected String fileHomePath;
    /**
     * 文件后缀
     */
    protected final String fileSuffix = ".meta";

    /**
     * 用户登录接口
     * 查询总文件夹下的用户文件夹，判断有没有该用户，如果没有的话提示注册，有的话执行登录逻辑
     *
     * @param user 用户实体类
     * @return
     */
    @Override
    public ResponseData userLogin(User user) {
        File file = new File(fileHomePath + user.getUserName());
        //判断用户文件夹是否存在，如果存在的话就执行登录，不存在表示用户没有注册
        if (!file.exists()) {
            //如果用户不存在,返回用户不存在
            ResponseDataUtil.buildError(ResultEnums.USER_NOT_FOUND);
        }
        //用户存在
        User userInfo = MetaFileUtil.metaRead(fileHomePath + user.getUserName() + fileSuffix, User.class);
        //检查用户密码 签发token
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userName", user.getUserName())
                .sign(Algorithm.HMAC256("mysecret"));
        return ResponseDataUtil.buildSuccess(file.exists());
    }
}
