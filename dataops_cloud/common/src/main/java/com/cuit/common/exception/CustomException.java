package com.cuit.common.exception;

import com.cuit.common.model.response.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/11 10:29 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    /**
     * 返回具体值
     */
    ResponseData responseData;

}
