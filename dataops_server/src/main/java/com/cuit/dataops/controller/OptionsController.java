package com.cuit.dataops.controller;

import com.cuit.dataops.api.OptionsApi;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @PostMapping("")
    public ResponseData submitOptions(@RequestBody SubmitOptionsRequest submitOptionsRequest) {
        return optionsService.runOptions(submitOptionsRequest);
    }
}
