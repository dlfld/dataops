package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description Python web service 注册到注册中心时提交的实体类信息
 * @Date 2021/9/20 2:21 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PyClient {
    /**
     * 客户端IP地址
     */
    private String ip;
    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 这个客户端所包含的模块信息
     */
    private List<Option> options = new ArrayList<>();
}
