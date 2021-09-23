package com.cuit.service_center.clients;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import com.cuit.common.pojo.base.Option;
import com.cuit.common.pojo.base.PyClient;
import com.cuit.common.rpc.RpcImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description 与客户端进行心跳并更新数据的类
 * @Date 2021/9/20 8:36 下午
 * @Version 1.0
 */
@Component
@Slf4j
public class HeartBeat {
    @Resource
    RpcImpl rpc;

    /**
     * 创建一个心跳
     *
     * @param pyClient 客户端提交的信息
     */
    @Async
    public void createHeartBeatTask(PyClient pyClient) {
        /**
         * 采用心跳机制，每五秒钟心跳一次
         * 心跳最主要的作用是获取到客户端的模块列表信息
         * 如果连续两次心跳都没有获取成功的话就在云端的列表里面删除这一个心跳
         */

        CronUtil.schedule("*/5 * * * * *", new Task() {
            /**
             * 心跳失败计数
             */
            Integer lossCount = 0;
            /**
             * 上一次心跳请求过来的模块数据
             */
            List<Option> lastOptions = null;
            /**
             * 心跳失败之后停止心跳的阈值
             */
            final int lossThreshold = 2;

            @Override
            public void execute() {
                /**
                 * 获取操作列表
                 */
                List<Option> options = null;
                try {
                    options = rpc.getOptions();
                } catch (Exception e) {
                    /**
                     * 代表请求超时
                     */
                    lossCount++;
                    log.info("客户端心跳失败");
                    //如果两次都有问题的话就停止当前心跳并在表中删除
                    if (lossCount >= lossThreshold) {
                        log.info("客户端心跳失败两次，删除客户端");
                        //在表中删除
                        ClientDataSet.removeClient(pyClient);
                        CronUtil.stop();
                    }
                    return;
                }
                /**
                 * 把返回的操作信息和列表封装到PyClient中
                 */
                log.info("获取到的options为：{}", options);
                //表示这是第一次心跳
                if (lastOptions == null) {
                    log.info("第一次心跳");
                    lastOptions = options;
                    pyClient.setOptions(options);
                    //添加到注册中心维护的表当中
                    ClientDataSet.addClient(pyClient);
                } else {
                    /**
                     * 如果不是第一次心跳
                     *  比较上一次的结果和当前的结果是不是一样的
                     *  如果是一样的就不管他
                     *  如果不是一样的就要把信息更新到表中去
                     */

                    log.info("客户端IP：{},端口:{}。心跳一次", pyClient.getIp(), pyClient.getPort());
                    boolean listEqual = HeartBeat.isListEqual(lastOptions, options);
                    //如果两个list不相同就更新
                    if (!listEqual) {
                        pyClient.setOptions(options);
                        ClientDataSet.updateClient(pyClient);
                    }
                }
            }
        });
        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

    public static <E> boolean isListEqual(List<E> list1, List<E> list2) {
        // 两个list引用相同（包括两者都为空指针的情况）
        if (list1 == list2) {
            return true;
        }
        // 两个list都为空（包括空指针、元素个数为0）
        boolean condition1 = list1 == null && list2 != null && list2.size() == 0;
        boolean condition2 = list2 == null && list1 != null && list1.size() == 0;
        if (condition1 || condition2) {
            return true;
        }

        // 两个list元素个数不相同
        if (list1.size() != list2.size()) {
            return false;
        }

        // 两个list元素个数已经相同，再比较两者内容
        // 采用这种可以忽略list中的元素的顺序
        // 涉及到对象的比较是否相同时，确保实现了equals()方法
        if (!list1.containsAll(list2)) {
            return false;
        }
        return true;
    }


}
