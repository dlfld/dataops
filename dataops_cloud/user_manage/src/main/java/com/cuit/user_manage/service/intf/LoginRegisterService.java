package com.cuit.user_manage.service.intf;

import com.cuit.common.model.base.user_manage.User;
import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/8 6:39 下午
 * @Version 1.0
 */
@Service
public interface LoginRegisterService {
    /**
     * 用户登录接口
     *
     * @param user 用户实体类
     * @return
     */
    ResponseData userLogin(User user);
}
