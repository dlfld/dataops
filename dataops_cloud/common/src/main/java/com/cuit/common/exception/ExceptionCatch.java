package com.cuit.common.exception;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/11 10:23 上午
 * @Version 1.0
 * //控制器增强
 */
@ControllerAdvice
public class ExceptionCatch {

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