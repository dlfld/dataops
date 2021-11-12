package com.cuit.user_manage.service.intf;

import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @Author wcw
 * @Description
 * @Date 2021/11/12
 * @Version 1.0
 */
@Service
public interface UserInfoService {
    /**
     * 暂定为获取主目录下面的所有用户信息
     * @return HashMap, key为用户的实际名称，value为账号
     */
    ResponseData getUserList();
}
