package com.cuit.scheduling.dispatch.schdul;//package cuit.scheduling.dispatch.schdul;

import com.cuit.common.pojo.base.Node;
import com.cuit.common.pojo.base.Param;
import com.cuit.common.pojo.base.ParamsBody2;
import com.cuit.common.pojo.base.Task;
import com.cuit.common.utils.SerializableUtil;
import com.cuit.scheduling.dispatch.taskFactory.impl.ResultImpl;
import com.cuit.common.rpc.RpcImpl;
import com.cuit.scheduling.dispatch.utils.ParamsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
     *
     * @param task 任务
     */
    @Override
    public void startDispatch(Task task) {
        //总的参数维护表
        ParamsBody2 allParams = task.getParamsBody2();
        //对task里面的节点数据进行调度
        //如果节点队列不为空就一直调度
        while (task.getNodeQueue().peek() != null) {
            //出队
            Node node = task.getNodeQueue().poll();
            //调度url为空的情况就是开始节点和终止节点
            if (StringUtils.isEmpty(node.getOptUrl())) {
                continue;
            }
            //获取当前节点前置节点的输出信息并封装为list,之后把当前节点前置节点的输出全部发送掉当前节点端
            /**
             *
             * ❌ 之前的代码是 不管是什么节点都是给全部的参数列表，在节点中进行参数的选择，这种办法和流程图表示流程和数据流有一定的偏差
             *
             * ✅ 现在有线连接就表示有数据传输
             * 具体操作 用全局参数表检测每一个参数项的产生节点是否在当前节点的前置节点列表当中，如果在的话就封装为一个list返回
             * 此处说到的前置应该理解为直接前置，也就是前置节点有直接的线连接到当前节点
             * 如果两个节点直接有线直接连接，那么就表示前一个节点的数据会流向后一个节点
             */
            List<Param> paramsList = allParams.getItems()
                    .parallelStream()
                    .filter(
                            param ->
                                    node.getPreNodeIds()
                                            .stream()
                                            .anyMatch(e -> StringUtils.equals(param.getNodeId(), e))
                    )
                    .collect(Collectors.toList());
            //封装数据传输类
            ParamsBody2 paramsBody2 = new ParamsBody2().setItems(paramsList);
            //远程调用并获取返回值
            ParamsBody2 resparam = rpc.httpRpcV2(node.getOptUrl(), paramsBody2);
            //更新参数表中的参数
            ParamsUtils.refreshParams(resparam, allParams);
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
        Task task = (Task) SerializableUtil.stringToObject(record.value(), Task.class);

        System.out.println("收到task->" + task);
        startDispatch(task);
    }

}
