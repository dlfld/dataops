package com.cuit.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/8/22 4:34 下午
 * @Version 1.0
 */

@AllArgsConstructor
@Getter
public enum SysEnums {
    /**
     * 开始节点的id
     */
    NODE_START_ID("1"),
    /**
     * 结束节点的id
     */
    NODE_END_ID("2"),
    /**
     * 开始节点的name
     */
    NODE_START_NAME("Start"),
    /**
     * 结束节点的name
     */
    NODE_END_NAME("End");

    private String value;



}
