package com.cuit.service_center.controller;

import com.cuit.api.service_center.ServiceHandleApi;
import com.cuit.common.pojo.base.PyClient;
import com.cuit.common.pojo.response.ResponseData;
import com.cuit.service_center.service.ServiceHandleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/9/20 2:34 下午
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/service_center")
public class ServiceHandleController implements ServiceHandleApi {
    @Resource
    ServiceHandleService serviceHandleService;

    /**
     * 客户端的注册
     *
     * @param pyClient
     * @return
     */
    @Override
    @PostMapping("/register")
    public ResponseData registerService(@RequestBody PyClient pyClient) {
        return serviceHandleService.registerClient(pyClient);
    }

    /**
     * 客户端检测注册中心是否掉线
     *   客户端也会对注册中心轮训，
     * @param pyClient
     * @return
     */
    @Override
    @PostMapping("/reconnect")
    public ResponseData reConnectService(@RequestBody PyClient pyClient) {
        return null;
    }

    /**
     * 获取计算端模块列表
     *
     * @return
     */
    @Override
    @GetMapping("/options")
    public ResponseData getOptions() {
        return serviceHandleService.getOptions();
    }




}
