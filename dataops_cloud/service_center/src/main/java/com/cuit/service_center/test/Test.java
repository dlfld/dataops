package com.cuit.service_center.test;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/9/23 12:48 下午
 * @Version 1.0
 */
public class Test  {
    public static void main(String[] args) {
        CronUtil.schedule("*/2 * * * * *", new Task() {
            @Override
            public void execute() {

            }
        });

        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}
