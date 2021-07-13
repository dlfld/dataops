package com.cuit.dataops.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * node 节点的 信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class NodeMsg {
    private String id;//node的id
    private String posiion;//node的位置
}
