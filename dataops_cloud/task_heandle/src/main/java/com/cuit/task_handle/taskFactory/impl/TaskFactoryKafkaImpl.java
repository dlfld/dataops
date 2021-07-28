package com.cuit.task_handle.taskFactory.impl;

import com.cuit.common.pojo.bo.Task;
import com.cuit.task_handle.taskFactory.intf.TaskFactoryIntf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * kafka的task队列实现
 *
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/27 8:46 上午
 * @Version 1.0
 */
@Slf4j
@Component
public class TaskFactoryKafkaImpl implements TaskFactoryIntf {
    @Resource
    private KafkaTemplate<Integer, Task> kafkaTemplate;
    @Value(value = "${taskKafkaTopic}")
    String topic;

    /**
     * 向队列中添加task
     * 添加成功返回true 添加失败返回false
     *
     * @param task 任务
     * @return 添加是否为空
     */
    @Override
    public boolean offer(Task task) {
        ListenableFuture<SendResult<Integer, Task>> future = kafkaTemplate.send(topic, 0, 0, task);
        /**
         * 异步发送的方式
         */
//        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Task>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onSuccess(SendResult<Integer, Task> integerTaskSendResult) {
//
//            }
//        });
        /**
         * 同步的方式发送
         */
        try {
            SendResult<Integer, Task> result = future.get();
            log.info("消息发送成功->" + task);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
