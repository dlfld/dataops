package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文件属性对象
 *
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/9 10:54 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileAttribute {
    /**
     * 文件属性名
     * csv:列
     * xml，json：属性
     */
    private String attributeName;
    /**
     * 文件属性含义
     */
    private String attributeMean;
}
