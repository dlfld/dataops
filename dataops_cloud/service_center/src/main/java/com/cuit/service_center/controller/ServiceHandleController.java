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
@RequestMapping("/service")
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
}
