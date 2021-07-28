package com.cuit.task_handle.service.impl;
import com.cuit.common.rpc.RpcImpl;

import com.cuit.common.pojo.bo.Task;
import com.cuit.common.pojo.request.SubmitOptionsRequest;
import com.cuit.common.pojo.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.task_handle.service.intf.OptionsService;
import com.cuit.task_handle.taskFactory.impl.TaskFactoryStaticImpl;
import com.cuit.task_handle.utils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class OptionsServiceImpl implements OptionsService {
    @Resource
    RpcImpl rpc;
    @Resource
    TaskFactoryStaticImpl taskFactoryStaticImpl;
    @Resource
    TaskUtils dataUtils;

    @Override
    public ResponseData getOptions() {
        return ResponseDataUtil.buildSuccess(rpc.getOptions());
    }


    /**
     * 这个版本是抽取出task层
     *
     * @param submitOptionsRequest
     * @return
     */
    @Override
    public ResponseData runTopoOptionsTaskMode(SubmitOptionsRequest submitOptionsRequest) {
        //获取当前请求的task
        Task task = dataUtils.buildTask(submitOptionsRequest);
        //将task添加到task队列
        taskFactoryStaticImpl.offer(task);
        log.info(task + "-> 执行结果");
        //下面是遍历task的node队列进行参数更新和调度
        //返回成功
        return ResponseDataUtil.buildSuccess("成功加入队列");
    }
}
