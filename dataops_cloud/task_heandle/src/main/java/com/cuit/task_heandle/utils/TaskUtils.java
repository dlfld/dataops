package com.cuit.task_heandle.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import com.cuit.common.pojo.Node;
import com.cuit.common.pojo.bo.Param;
import com.cuit.common.pojo.bo.ParamsBody2;
import com.cuit.common.pojo.bo.Task;
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
     * @param submitOptionsRequest
     * @return
     */
    public Task buildTask(SubmitOptionsRequest submitOptionsRequest) {
        String filePath = dataFilePath + "/" + submitOptionsRequest.dataFileName;
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(filePath));

        //初始化一个task 并初始化task的参数表  和task的id （唯一标识符）
        Task task = new Task().setParamsBody2(new ParamsBody2(new ArrayList<Param>() {{
            add(new Param().setDesc("start desc").setVersion(0).setObject(data.getRows()));
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
