package com.cuit.service_center.clients;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import com.cuit.common.model.base.ops.PyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description 轮训客户端表，删除不健康的客户端信息
 * @Date 2021/9/20 8:36 下午
 * @Version 1.0
 */
@Component
@Slf4j
@Order(value = 1)
public class HeartBeat implements ApplicationRunner {
    /**
     * 每隔15s检测有没有十秒钟以上没有更新的客户端，如果有的话就删掉
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //删除客户端的时间阈值
        final Long removeTimeLimit = 15 * 1000L;
        CronUtil.schedule("*/15 * * * * *", new Task() {
            @Override
            public void execute() {
                log.info("开始扫描全部客户端的健康性");
                List<PyClient> clients = new ArrayList<>(ClientDataSet.getClients());
                if (clients.size() == 0) {
                    return;
                }
                clients.parallelStream().forEach(item -> {
                    Long currentTimeMillis = System.currentTimeMillis();
                    //如果心跳已经有10s没有更新了
                    log.info("断线时间：{}", currentTimeMillis - item.getLastHeartbeat());
                    log.info("上一次心跳{}", new Date(item.getLastHeartbeat()));
                    if ((currentTimeMillis - item.getLastHeartbeat()) >= removeTimeLimit) {
                        //删除客户端
                        log.info("删除客户端{},IP={},Port={}", item.getServiceName(), item.getIp(), item.getPort());
                        ClientDataSet.removeClient(item);
                    }
                });
            }
        });
        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}
