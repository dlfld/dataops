package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Option {
    /**
     * python服务的请求路径
     */
    private String optUrl;
    /**
     * python服务的名字
     */
    private String optName;
    /**
     * python服务的简介
     */
    private String optDesc;
}
