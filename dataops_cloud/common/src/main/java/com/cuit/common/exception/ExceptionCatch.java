package com.cuit.common.exception;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.gateway.GateWayValues;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/11 10:23 上午
 * @Version 1.0
 * //控制器增强
 */
@ControllerAdvice
@Slf4j
public class ExceptionCatch  {


    /**
     * 捕获CustomException此类异常
     *
     * @param customException 自定义异常
     * @return 返回异常通知
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseData customException(CustomException customException) {
        return ResponseDataUtil.buildError(customException.getResponseData());
    }

    @ExceptionHandler(OperationRunnerException.class)
    @ResponseBody
    public void customDefineException(OperationRunnerException operationRunnerException) {
        //获取用户的联系方式
        String userContact = operationRunnerException.getUserContact();
        //todo 给用户发消息 说当前节点运行失败
    }

    /**
     * 捕获其他的异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData exception(Exception exception) {
        exception.printStackTrace();
        return ResponseDataUtil.buildError(ResultEnums.SYSTEM_ERROR);
    }


}