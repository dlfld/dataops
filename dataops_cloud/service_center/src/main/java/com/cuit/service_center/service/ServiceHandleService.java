package com.cuit.service_center.service;

import com.cuit.common.pojo.base.Option;
import com.cuit.common.pojo.base.PyClient;
import com.cuit.common.pojo.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.service_center.clients.ClientDataSet;
import com.cuit.service_center.clients.HeartBeat;
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
public class ServiceHandleService {
    @Resource
    HeartBeat heartBeat;

    /**
     * 客户端注册
     *
     * @param pyClient 客户端注册提交过来的信息
     * @return 注册成功返回
     */
    public ResponseData registerClient(PyClient pyClient) {
        heartBeat.createHeartBeatTask(pyClient);
        return ResponseDataUtil.buildSuccess();
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
