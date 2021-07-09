package com.cuit.dataops.controller;

import com.cuit.dataops.api.OptionsApi;
import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.service.intf.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * options相关接口
 */
@CrossOrigin
@RestController
@RequestMapping("/options")
public class OptionsController implements OptionsApi {
    @Autowired
    OptionsService optionsService;

    /**
     * 获取options列表
     * （获取所有方法列表）
     *
     * @return
     */
    @Override
    @GetMapping("")
    public ResponseData getOptionsList() {
        return optionsService.getOptions();
    }


    @Override
    @PostMapping("/topo2")
    public ResponseData submitOptions3(@RequestBody SubmitOptionsRequest submitOptionsRequest) {
        return optionsService.runTopoOptions3(submitOptionsRequest);
    }
}
