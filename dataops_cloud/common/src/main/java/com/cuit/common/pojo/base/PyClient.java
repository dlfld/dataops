package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * 当前客户端上一次心跳的时间戳
     */
    private Long lastHeartbeat = 0L;

    /**
     * 重写这个方法主要是判断注册中心维护的客户端表里面判断是否存在某个客户端
     * 要根据ip和port等相等进行判断因此重新equals方法
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PyClient)) {
            return false;
        }
        PyClient pyClient = (PyClient) o;
        return (Objects.equals(this.getIp(), pyClient.getIp()) && Objects.equals(this.getPort(), pyClient.getPort()));
    }
}
