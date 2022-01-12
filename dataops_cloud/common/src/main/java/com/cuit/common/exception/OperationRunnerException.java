package com.cuit.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/1/12 7:57 PM
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRunnerException extends RuntimeException{
    private static final long serialVersionUID = 7293579987251203267L;
    /**
     * 用户联系方式
     */
    private String userContact;
}
