package com.cuit.task_handle.controller;

import com.cuit.api.task_handle.OptionsApi;
import com.cuit.common.model.base.ops.vo.SubmitOptionsRequest;
import com.cuit.common.model.response.ResponseData;
import com.cuit.task_handle.service.intf.OptionsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * options相关接口
 * @author dailinfeng
 */
@CrossOrigin
@RestController
@RequestMapping("/options")
public class OptionsController implements OptionsApi {
    @Resource
    OptionsService optionsService;

    @Override
    @PostMapping("/topo2")
    public ResponseData submitOptions(@RequestBody SubmitOptionsRequest submitOptionsRequest) {
        return optionsService.runTopoOptionsTaskMode(submitOptionsRequest);
    }
}
