package com.cuit.common.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 第二个版本的参数
 *
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ParamsBody2 implements Serializable {
    /**
     * 参数列表
     */
    List<Param> items = new ArrayList<>();
    /**
     * 当前节点的id，在模块调用的时候需要传递给计算端的
     */
    private String curNodeId;
    /**
     * 单个节点的配置信息
     */
    private NodeConfig nodeConfig;
}
