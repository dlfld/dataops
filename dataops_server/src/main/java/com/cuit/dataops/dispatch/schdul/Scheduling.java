package com.cuit.dataops.dispatch.schdul;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cuit.dataops.dispatch.rpc.RpcImpl;
import com.cuit.dataops.dispatch.rpc.notify.QQBotRpc;
import com.cuit.dataops.dispatch.rpc.notify.bo.Message;
import com.cuit.dataops.dispatch.taskFactory.impl.ResultImpl;
import com.cuit.dataops.dispatch.taskFactory.impl.TaskFactoryStaticImpl;
import com.cuit.dataops.dispatch.utils.DataUtils;
import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.bo.ParamsBody2;
import com.cuit.dataops.pojo.bo.Task;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 调度中心
 * 计划是springboot启动的时候 调度中心的调度算法就开始启动
 * 每隔一定的时间就扫描一遍task队列是否有任务，如果有任务就进行任务并把返回值保存到结果集当中去
 *
 * @author dailinfeng
 */
@Component
@Slf4j
@Order(value = 1)
public class Scheduling extends AbstractSchedulingIntf implements ApplicationRunner {
    @Resource
    TaskFactoryStaticImpl taskFactoryStatic;
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

    @Override
    public void startDispatch() {
        //如果task队列不为空就运行
        while (!taskFactoryStatic.isEmpty()) {
            //获取task  但是不删除  让他在调度运行的时候也保存在队列里
            Task task = taskFactoryStatic.peek();
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
                task.setParamsBody2(DataUtils.refreshParams(res, task.getParamsBody2()));
            }
            //到这一步 一个task的调度就算完成了  下面的就是保存结果并通知用户

            //保存到任务队列里
            String filename = result.saveTask(task);
            //如果保存结果成功
            if (StringUtils.isNotEmpty(savePath)) {
                result.notifyUser(task.getUserContact(), filename);
                taskFactoryStatic.poll();//在任务队列里删除
            }
        }
    }


    /**
     * springboot启动之后 会开始运行这个方法
     * 10 秒检测一次任务队列是否为空，如果不为空的话就执行任务队列里面的任务
     *
     * @param args canshu
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            log.info("扫描");
            startDispatch();
            Thread.sleep(1000 * 10);
        }
    }
}
