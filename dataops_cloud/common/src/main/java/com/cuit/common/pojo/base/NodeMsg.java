package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * node 节点的 信息
 *
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NodeMsg {
    /**
     * node的id
     */
    private String id;
    /**
     * node的位置
     */
    private String posiion;
    /**
     * 节点的名字
     */
    private String name;
}
