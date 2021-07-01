package com.cuit.dataops.service;

import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;

import org.springframework.stereotype.Component;


@Component
public interface OptionsService {
    //获取所有的option
    ResponseData getOptions();

    //执行options
    ResponseData runOptions(SubmitOptionsRequest submitOptionsRequest);

}
