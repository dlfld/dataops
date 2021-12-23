package com.cuit.user_manage.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.user_manage.User;
import com.cuit.common.model.base.user_manage.dto.UserLoginDto;
import com.cuit.common.model.base.user_manage.vo.UserLoginVo;
import com.cuit.common.model.base.user_manage.vo.UserRegisterVo;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.user_manage.service.intf.LoginRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

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
    protected static final String fileSuffix = ".meta";
    /**
     * Issuer
     */
    protected static final String issuer = "auth0";
    /**
     * 用户名
     */
    protected static final String userName = "userName";
    /**
     * 加密 密钥
     */
    protected static final String key = "idse2021";
    /**
     * 用户分享目录
     */
    protected static final String sharePath = "/share";
    /**
     * 用户项目目录
     */
    protected static final String projectPath = "/project";

    /**
     * 用户登录接口
     * 查询总文件夹下的用户文件夹，判断有没有该用户，如果没有的话提示注册，有的话执行登录逻辑
     *
     * @param user 用户实体类
     * @return
     */
    @Override
    public ResponseData userLogin(UserLoginVo user) {

        File file = new File(fileHomePath + user.getUserName());
        //判断用户文件夹是否存在，如果存在的话就执行登录，不存在表示用户没有注册
        if (!file.exists()) {
            //如果用户不存在,返回用户不存在
            return ResponseDataUtil.buildError(ResultEnums.USER_NOT_FOUND);
        }
        //用户存在
        User userInfo = MetaFileUtil.metaRead(fileHomePath + user.getUserName() + fileSuffix, User.class);
        //对密码进行加密
        String userPass = DigestUtil.md5Hex(user.getPassword());
        //如果密码不匹配的话 返回密码错误
        if (!Objects.equals(userPass, userInfo.getPassword())) {
            return ResponseDataUtil.buildError(ResultEnums.PASSWORD_ERROR);
        }
        //密码匹配
        //检查用户密码 签发token
        String token = JWT.create()
                .withIssuer(issuer)
                .withClaim(userName, user.getUserName())
                .sign(Algorithm.HMAC256(key));
        //回传前端数据类
        UserLoginDto userLoginDto = new UserLoginDto();
        BeanUtils.copyProperties(userInfo, userLoginDto);
        userLoginDto.setToken(token);
        return ResponseDataUtil.buildSuccess(userLoginDto);
    }

    /**
     * 用户注册逻辑
     *
     * @param user 用户实体类
     * @return 注册成功标注
     */
    @Override
    public ResponseData userRegister(UserRegisterVo user) {
        File file = new File(fileHomePath + user.getUserName());
        //判断用户文件夹是否存在,如果存在的话表示用户已经注册
        if (file.exists()) {
            //如果文件存在，则表示用户已经注册
            ResponseDataUtil.buildError(ResultEnums.USER_EXIST);
        }
        //用户不存在的话，就执行注册逻辑
        //创建文件夹
        file.mkdir();
        //创建用户的分享文件夹
        new File(fileHomePath + user.getUserName() + sharePath).mkdir();
//        创建用户项目文件夹
        new File(fileHomePath + user.getUserName() + projectPath).mkdir();
        //用户密码加密
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        User registerUser = new User();
        BeanUtils.copyProperties(user, registerUser);
        MetaFileUtil.metaWrite(fileHomePath + user.getUserName() + fileSuffix, registerUser);
        log.info(MetaFileUtil.metaRead(fileHomePath + user.getUserName() + fileSuffix, User.class).toString());
        return ResponseDataUtil.buildSuccess();
    }

}
