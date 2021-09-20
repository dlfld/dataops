package com.cuit.service_center.clients;

import com.cuit.common.pojo.base.PyClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author dailinfeng
 * @Description 与客户端进行心跳并更新数据的类
 * @Date 2021/9/20 8:36 下午
 * @Version 1.0
 */
@Component
public class HeartBeat {
    /**
     * 创建一个心跳
     * @param pyClient 客户端提交的信息
     */
    @Async
    public void createHeartBeatTask(PyClient pyClient) {
        
    }
}
