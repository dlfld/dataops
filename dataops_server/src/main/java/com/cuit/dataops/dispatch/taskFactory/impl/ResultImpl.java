package com.cuit.dataops.dispatch.taskFactory.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cuit.dataops.dispatch.rpc.notify.QQBotRpc;
import com.cuit.dataops.dispatch.rpc.notify.bo.Message;
import com.cuit.dataops.dispatch.taskFactory.intf.ResultFactoryIntf;
import com.cuit.dataops.pojo.bo.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dailinfeng
 * 结果处理的实现累
 * 当前的处理是通过qq通知的方式实现的
 */
@Component
public class ResultImpl implements ResultFactoryIntf {

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
    QQBotRpc qqBotRpc;

    /**
     * 保存task
     * <p>
     * 当前的保存是 以task的id为文件名保存到json文件内 取出的时候以文件名拼接为url取出结果
     *
     * @param task 任务
     * @return 保存之后返回的连接
     */
    @Override
    public String saveTask(Task task) {
        //把task转为json字符串
        JSONObject taskJsonObj = JSONUtil.parseObj(task);
        String taskJsonStr = taskJsonObj.toStringPretty();

        boolean exist = FileUtil.exist(savePath);
        //如果文件不存在则创建文件
        if (!exist) {
            FileUtil.mkdir(savePath);
        }
        String taskFilePath = savePath + "/" + task.getTaskId() + "-result" + ".json";
        FileUtil.touch(taskFilePath);
        try {
            FileWriter writer = new FileWriter(taskFilePath);
            writer.write(taskJsonStr);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task.getTaskId() + ".json";
    }

    /**
     * 提醒用户操作完成
     * 当前的实现方式是发送qq消息的方式
     *
     * @param contact 联系方式
     * @param content url内容
     */
    @Override
    public boolean notifyUser(String contact, String content) {
        String downTaskUrl = baseDownUrl + "/" + content;
        Message message = new Message().setMessage("您的OPS已经计算完成！\n结果下载链接是：\n" + downTaskUrl).setUser_id(contact);
        //发消息
        //内网的时候  再开 不然通知失败会直接报bug
        qqBotRpc.nodityUser(message);
        return true;
    }
}
