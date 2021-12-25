package com.cuit.common.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 7:23 PM
 * @Version 1.0
 */
public class RequestUtils {
    /**
     * header里面用户名的key
     */
    public static final String  userNameKey = "userName";

    /**
     * 获取请求头里面的用户名
     * @param request 请求体
     * @return
     */
    public static String getUserName(HttpServletRequest request){
        return request.getHeader(userNameKey);
    }


}
