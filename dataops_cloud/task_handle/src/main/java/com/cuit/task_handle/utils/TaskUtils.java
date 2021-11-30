package com.cuit.task_handle.utils;

import com.cuit.common.enums.SysEnums;
import com.cuit.common.model.base.ops.*;
import com.cuit.common.model.request.SubmitOptionsRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 这个工具类是用来处理数据相关的工具类
 *
 * @author dailinfeng
 */
@Slf4j
@Component
public class TaskUtils {
    @Value(value = "${data.dataPath}")
    public String dataFilePath;

    /**
     * 根据传入的参数进行task的封装 封装之后进行返回
     *
     * @param submitOptionsRequest 前端传过来的参数
     * @return
     */
    public Task buildTask(SubmitOptionsRequest submitOptionsRequest) {

        /**
         * 前一个版本是直接读取csv的数据进行传输，当前版本只需要传数据的元数据，不需要读取出来了
         * dataFileName 里面存的就是上传文件之后文件在硬盘上的实际位置
         */
        //封装参数列表
        ParamsBody2 paramsBody2 = new ParamsBody2()
                .setItems(new ArrayList<>() {{
                    add(new Param()
                            //设置desc
                            .setDesc(submitOptionsRequest.getParamsDesc())
                            //设置下载链接
                            .setDownloadUrl(submitOptionsRequest.getDownloadUrl())
                            //设置物理位置
                            .setLocation(submitOptionsRequest.getDataFileFullPath())
                            //设置文件名
                            .setFileName(submitOptionsRequest.dataFileName)
                            //设置输出数据节点ID 第一个输出节点定义为start节点
                            .setCurNodeId(SysEnums.NODE_START_ID.getValue())
                    );
                }});

        Task task = new Task()
                //初始化taskid
                .setTaskId(UUID.randomUUID().toString())
                //填入用户联系方式
                .setUserContact(submitOptionsRequest.getUserContact())
                //填入参数列表
                .setParamsBody2(paramsBody2);
        //获取前端已经经过拓扑排序之后的排序列表
        List<Node> sortList = submitOptionsRequest.getNodes();
        /**
         * 通过connection的列表获取当前节点的前置节点
         *   具体实现：
         *      遍历node的列表查询当前节点的前置节点，并保存到当前节点的信息中
         */
        List<Connection> connections = submitOptionsRequest.getConnections();
        //给node节点添加前置节点信息
        List<Node> nodes = sortList.parallelStream().map(node -> {
            //前置节点的检索
            List<String> preNodes = connections
                    .parallelStream()
                    .filter(connection -> StringUtils.equals(connection.getDestination().getId(), node.getId()))
                    .map(connection -> connection.getSource().getId())
                    .collect(Collectors.toList());
            //前置节点的添加
            node.getPreNodeIds().addAll(preNodes);
            return node;
        }).collect(Collectors.toList());
        //把排序列表添加到队列当中去
        nodes.forEach(task.nodeQueue::offer);
        return task;
    }
}
