package com.cuit.dataops.handler;

//import com.cuit.dataops.aop.ParamOutAspect;
import com.cuit.dataops.enums.ResultEnums;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.utils.ResponseDataUtil;
//import com.yilers.jwtp.exception.ErrorTokenException;
//import com.yilers.jwtp.exception.ExpiredTokenException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 拦截Controller方法默认返回参数，统一处理返回值/响应体
 */
@ControllerAdvice(basePackages = {"com.cuit.travel.controller"})
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        long timeDuration = System.currentTimeMillis() - ParamOutAspect.startTime.get();// 计算请求花费的时间
        ResponseData responseData = (ResponseData) o;
//        responseData.setCost(timeDuration);
        return responseData;
    }
    /**
     * 逻辑计算是否执行beforeBodyWrite方法  返回true则执行返回false则不执行
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
    /**
     * 捕获token为空的异常 单独返回
     * @param e
     * @return
     */
//    @ResponseBody
//    @ExceptionHandler(value = ErrorTokenException.class)
//    public ResponseData errorTokenException(ErrorTokenException e){
//        System.out.println(e);
//        return ResponseDataUtil.buildError(ResultEnums.HAVE_NO_TOKEN);
//    }
//
//    /**
//     * token过期
//     * @param e
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(value = ExpiredTokenException.class)
//    public ResponseData expiredTokenException(ExpiredTokenException e){
//        System.out.println(e);
//        return ResponseDataUtil.buildError(ResultEnums.HAVE_NO_TOKEN);
//    }
    /**
     * 全局异常捕获
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseData globalException(Exception e) {
        System.out.println(e);
        return ResponseDataUtil.buildError("服务器异常!");
    }
}
