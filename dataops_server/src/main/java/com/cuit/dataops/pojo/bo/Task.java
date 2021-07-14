package com.cuit.dataops.pojo.bo;

import com.cuit.dataops.pojo.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.Queue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class Task {
    public ParamsBody2 paramsBody2; //参数表
    public Queue<Node> nodeQueue = new LinkedList<>();
}
