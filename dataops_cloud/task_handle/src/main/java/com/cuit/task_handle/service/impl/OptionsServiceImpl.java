package com.cuit.task_handle.service.impl;


import com.cuit.common.model.base.ops.Task;
import com.cuit.common.model.base.ops.vo.SubmitOptionsRequest;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.rpc.RpcImpl;
import com.cuit.task_handle.service.intf.OptionsService;
import com.cuit.task_handle.taskFactory.impl.TaskFactoryKafkaImpl;
import com.cuit.task_handle.utils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dailinfeng
 */
@Slf4j
@Component
public class OptionsServiceImpl implements OptionsService {
    @Resource
    RpcImpl rpc;
    @Resource
    TaskFactoryKafkaImpl taskFactoryKafka;
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
        taskFactoryKafka.offer(task);
        log.info("-> 执行结果");
        log.info(task+"");
        //下面是遍历task的node队列进行参数更新和调度
        //返回成功
        return ResponseDataUtil.buildSuccess("成功加入队列");
    }
}
