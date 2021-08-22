package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Task implements Serializable {
    /**
     * task的唯一标识符
     */
    public String taskId;
    /**
     * 参数表
     */
    public ParamsBody2 paramsBody2;
    /**
     * 节点表
     */
    public Queue<Node> nodeQueue = new LinkedList<>();

    /**
     * 用户的联系方式
     */
    public String userContact;
}
