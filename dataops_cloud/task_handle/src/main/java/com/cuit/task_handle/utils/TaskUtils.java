package com.cuit.task_handle.utils;

import com.cuit.common.pojo.base.Node;
import com.cuit.common.pojo.base.Param;
import com.cuit.common.pojo.base.ParamsBody2;
import com.cuit.common.pojo.base.Task;
import com.cuit.common.pojo.request.SubmitOptionsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Task task = new Task().setParamsBody2(new ParamsBody2(new ArrayList<Param>() {{
                    add(new Param()
                            .setDesc(submitOptionsRequest.paramsDesc)
                            .setVersion(0)
                            .setLocation(submitOptionsRequest.getDataFileName()));
                }}))
                .setTaskId(UUID.randomUUID().toString())
                .setUserContact(submitOptionsRequest.getUserContact());
        //获取前端已经经过拓扑排序之后的排序列表
        List<Node> sortList = submitOptionsRequest.getNodes();
        //把排序列表添加到队列当中去
        sortList.forEach(task.nodeQueue::offer);
        return task;
    }

}
