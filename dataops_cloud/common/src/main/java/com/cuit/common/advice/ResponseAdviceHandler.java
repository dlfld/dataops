package com.cuit.common.advice;

import com.cuit.common.model.base.gateway.GateWayValues;
import com.cuit.common.model.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/24 2:51 PM
 * @Version 1.0
 */
@Slf4j
@Component
public class ResponseAdviceHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseData responseData = (ResponseData) o;
        List<String> strings = serverHttpRequest.getHeaders().get(GateWayValues.startTime);
        System.out.println(strings);
        log.info("进来了");
        return responseData;
    }
}
