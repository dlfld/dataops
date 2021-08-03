package com.cuit.scheduling.dispatch.schdul;//package cuit.scheduling.dispatch.schdul;

import com.cuit.common.pojo.Node;
import com.cuit.common.pojo.bo.ParamsBody2;
import com.cuit.common.pojo.bo.Task;
import com.cuit.common.utils.SerializableUtil;
import com.cuit.scheduling.dispatch.taskFactory.impl.ResultImpl;
import com.cuit.scheduling.dispatch.utils.ParamsUtils;
import com.cuit.common.rpc.RpcImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 调度中心
 * 计划是springboot启动的时候 调度中心的调度算法就开始启动
 * 每隔一定的时间就扫描一遍task队列是否有任务，如果有任务就进行任务并把返回值保存到结果集当中去
 *
 * @author dailinfeng
 */
@Component
//@Slf4j
public class Scheduling extends AbstractSchedulingIntf {
    @Resource
    RpcImpl rpc;

    /**
     * 文件保存的路径
     */
    @Value(value = "${data.save}")
    String savePath;
    /**
     * 下载结果服务器的url
     */
    @Value(value = "${data.serverBaseUrl}")
    String baseDownUrl;

    @Resource
    ResultImpl result;
    //根据task队列进行调度的调度中心代码

    /**
     * 对task内的模块进行调度并保存结果
     * @param task  任务
     */
    @Override
    public void startDispatch(Task task) {
        //对这个task里面的节点进行调度
        while (task.nodeQueue.peek() != null) {
            Node node = task.nodeQueue.poll();
            if (StringUtils.isEmpty(node.getOptUrl())) {
                continue;
            }
            //获取调用的参数并封装
            ParamsBody2 paramsBody2 = task.getParamsBody2();
            //远程调用并返回结果
            ParamsBody2 res = rpc.httpRpcV2(node.getOptUrl(), paramsBody2);
            //进行参数的更新
            task.setParamsBody2(ParamsUtils.refreshParams(res, task.getParamsBody2()));
        }
        //到这一步 一个task的调度就算完成了  下面的就是保存结果并通知用户

        //保存到文件并返回文件名
        String filename = result.saveTask(task);
        //如果保存结果成功
        if (StringUtils.isNotEmpty(filename)) {
            result.notifyUser(task.getUserContact(), filename);
        }
    }


    /**
     * 现在是整一个Kafka的客户端，收到消息之后就开始运行task
     *
     * @throws Exception
     */
    @KafkaListener(topics = "topic-task-queue")
    public void onMessage(ConsumerRecord<Integer, String> record) {
//        try {
//            Thread.sleep(1000*10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Task task =(Task) SerializableUtil.stringToObject(record.value(),Task.class);
        System.out.println("收到task->"+task);
        startDispatch(task);
    }

}
