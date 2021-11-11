package com.cuit.common.exception;

import com.cuit.common.model.response.ResponseData;

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
}
