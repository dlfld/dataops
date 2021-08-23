package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Param implements Serializable {
    /**
     * 参数的描述  计算端根据参数的描述来进行参数的检索
     */
    private String desc;
    /**
     * 参数当前的版本号，后期根据版本号进行参数的更新
     */
    private Integer version = 0;
    /**
     * 数据在物理机上的存储地址
     */
    private String location;
    /**
     * 物理机地址
     */
    private List<String> hosts;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件下载链接
     */
    private String downloadUrl;
    /**
     * 输出数据节点id
     */
    private String nodeId;
}
