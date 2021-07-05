package com.cuit.dataops.service;

import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface OptionsService {
    //获取所有的option
    ResponseData getOptions();

    //执行options
    ResponseData runOptions(SubmitOptionsRequest submitOptionsRequest);

    ResponseData runTopoOptions(List<Node> nodes);
}
