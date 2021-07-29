package com.cuit.common.pojo.bo;

import com.cuit.common.pojo.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class Task implements Serializable {
    public String taskId; //task的唯一标识符
    public ParamsBody2 paramsBody2; //参数表
    public Queue<Node> nodeQueue = new LinkedList<>();
    public String userContact;//用户的联系方式
}
