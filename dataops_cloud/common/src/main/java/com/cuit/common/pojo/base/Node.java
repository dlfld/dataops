package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Node implements Serializable {
    /**
     * 前端组件的id
     */
    private String id;
    /**
     * 请求python端的url
     */
    private String optUrl;
    /**
     * 单个节点的配置
     */
    private NodeConfig nodeConfig;
    /**
     * 当前节点的前置节点id列表
     * 前端不传
     */
    private List<String> preNodeIds = new ArrayList<>();
}
