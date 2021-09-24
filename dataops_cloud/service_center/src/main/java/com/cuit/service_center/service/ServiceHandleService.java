package com.cuit.service_center.service;

import com.cuit.common.pojo.base.Option;
import com.cuit.common.pojo.base.PyClient;
import com.cuit.common.pojo.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.service_center.clients.ClientDataSet;
import com.cuit.service_center.clients.HeartBeat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/9/20 2:36 下午
 * @Version 1.0
 */
@Service
@Slf4j
public class ServiceHandleService {
    @Resource
    HeartBeat heartBeat;

    /**
     * 客户端注册
     * V1：客户端向注册中心报告自己的信息之后注册中心创建一个心跳线程一直对客户端进行请求
     * V2：  参考 https://www.cnblogs.com/xiaodou00/p/15069015.html  客户端对注册中心进行注册，注册成功之后一直进行轮训
     * 并刷新健康状态
     * 首先客户端向注册中心发送心跳连接，默认是5s一次
     * 注册中心收到连接之后
     * a.判断客户端是否注册过
     * 1注册过：在客户端表中判断模块列表是否有更新，如果有更新的话就进行更新，没有就不更新，然后返回下一次心跳的间隔。
     * 2没有注册过：加入客户端表
     * b.给当前客户端大上当前时间的时间戳
     * 注册中心每隔15s检测如果遇到时间戳已经过去10s的就直接删除掉当前节点
     *
     * @param pyClient 客户端注册提交过来的信息
     * @return 注册成功返回
     */
    public ResponseData registerClient(PyClient pyClient) {
        Boolean exists = ClientDataSet.exists(pyClient);
//        log.info("原来的客户端表中是否存在当前客户端：{}", exists);
        //更新时间戳
        pyClient.setLastHeartbeat(System.currentTimeMillis());

        //如果当前客户端已经在维护的表当中
        if (exists) {
            //更新客户端信息
            ClientDataSet.updateIfChangeed(pyClient);
        } else {
            //如果不在的话就添加到里面
            ClientDataSet.addClient(pyClient);
        }

//        log.info("收到了心跳连接{}", pyClient);
        int nextHeartBeat = 5;
        return ResponseDataUtil.buildSuccess(nextHeartBeat);
    }

    /**
     * 获取所有在线的options
     *
     * @return options列表
     */
    public ResponseData getOptions() {
        List<Option> options = ClientDataSet.getOptions();
        return ResponseDataUtil.buildSuccess(options);

    }
}
