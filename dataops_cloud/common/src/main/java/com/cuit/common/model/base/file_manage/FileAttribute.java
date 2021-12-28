package com.cuit.common.model.base.file_manage;

import com.cuit.common.model.base.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class FileAttribute  implements Serializable {
    private static final long serialVersionUID = 8412178142051620364L;
    /**
     * 文件属性名
     * csv:列
     * xml，json：属性
     */
    private String attributeName;
    /**
     * 文件属性含义  <可选填>
     */
    private String attributeMean;


}
