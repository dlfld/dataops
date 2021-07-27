package com.cuit.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class Option {
    private String optUrl; //python服务的请求路径
    private String optName;//python服务的名字
    private String optDesc;//python服务的简介
}
