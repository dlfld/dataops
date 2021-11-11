package com.cuit.user_manage.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.user_manage.User;
import com.cuit.common.model.base.user_manage.dto.UserLoginDto;
import com.cuit.common.model.base.user_manage.vo.UserLoginVo;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.user_manage.service.intf.LoginRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.cuit.common.utils.MetaFileUtil.isMateFile;

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
            ResponseDataUtil.buildError(ResultEnums.PASSWORD_ERROR);
        }
        //密码匹配
        //检查用户密码 签发token
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userName", user.getUserName())
                .sign(Algorithm.HMAC256("idse2020"));
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
    public ResponseData userRegister(User user) {
        File file = new File(fileHomePath + user.getUserName());
        //判断用户文件夹是否存在,如果存在的话表示用户已经注册
        if (file.exists()) {
            //如果文件存在，则表示用户已经注册
            ResponseDataUtil.buildError(ResultEnums.USER_EXIST);
        }
        //用户不存在的话，就执行注册逻辑
        //创建文件夹
        file.mkdir();
        //用户密码加密
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        MetaFileUtil.metaWrite(fileHomePath + user.getUserName() + fileSuffix, user);
        log.info(MetaFileUtil.metaRead(fileHomePath + user.getUserName() + fileSuffix, User.class).toString());
        return ResponseDataUtil.buildSuccess();
    }



    /**
     * 获取指定目录下的用户信息。
     * @return 用户信息
     */
    @Override
    public ResponseData getUserList(){
        Map<String, String> userMap = new HashMap<String, String>();
        // 建立当前目录中文件的File对象
        File file = new File(fileHomePath);
        // 取得代表目录中所有文件的File对象数组
        File[] list = file.listFiles();
        // 遍历当前目录的用户
        for (int i = 0; i < list.length; i++) {
            if (isMateFile(list[i].getName())){
                //获取用户对应mate文件中的信息
                User user = MetaFileUtil.metaRead(list[i].getPath(),User.class);
                if (Objects.isNull(user)){
                    //抛出异常
                    System.out.println("该文件用户信息不存在");
                }else {
                    userMap.put(user.getRealName(),user.getUserName());
                }
            }
        }
        return ResponseDataUtil.buildSuccess(userMap);
    }
}
