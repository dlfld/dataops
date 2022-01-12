package com.cuit.common.exception;

import com.cuit.common.model.response.ResponseData;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/11 10:33 上午
 * @Version 1.0
 */
public class ExceptionCast {
    public static void cast(ResponseData responseData) {
        throw new CustomException(responseData);
    }

    public static void customDefineCast(RuntimeException exception) {
        throw exception;
    }
}
