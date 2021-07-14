package com.cuit.dataops.dispatch;

import com.cuit.dataops.dispatch.rpc.RpcImpl;
import com.cuit.dataops.dispatch.taskFactory.TaskFactoryStaticImpl;
import com.cuit.dataops.dispatch.utils.DataUtils;
import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.bo.ParamsBody2;
import com.cuit.dataops.pojo.bo.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 调度中心
 */
@Component
public class Scheduling {
    @Resource
    TaskFactoryStaticImpl taskFactoryStatic;
    @Resource
    RpcImpl rpc;

    //根据task队列进行调度的调度中心代码
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
            taskFactoryStatic.poll();
            /**
             * 调度结果应该怎么返回？
             *              1。ws？长等待
             *              2。轮训？
             *              3。结果直接发邮件？
             * 调度结果以什么格式返回？
             *
             * 怎么判断一个task是否结束？
             *         每个task应该有一个独一无二的标识符
             *         如果是多线程应该标识这个task已经开始了不用再开始？
             *task执行完成之后的结构是否应该保存 应该怎么保存？
             */


        }
    }
}
