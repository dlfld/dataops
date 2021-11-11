package com.cuit.user_manage.interceptor;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/11 9:53 上午
 * @Version 1.0
 */
@Slf4j
@Component
public class OpsInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String methodName = method.getName();
        //获取请求头
        Iterator<String> userName = request.getHeaders("userName").asIterator();
        //如果获取的请求头可以遍历的话说明请求头不为空，也就表示经过了网关，经过了网关说明token是经过校验的
        if (userName.hasNext()) {
            return true;
        }
        ExceptionCast.cast(ResultEnums.USER_NO_LOGIN);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}