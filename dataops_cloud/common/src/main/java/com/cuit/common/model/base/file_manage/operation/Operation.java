package com.cuit.common.model.base.file_manage.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author dailinfeng
 * @Description 具体的描述文件操作的实体类
 * @Date 2021/12/28 10:55 AM
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Operation implements Serializable {
    private static final long serialVersionUID = 3791387264703044514L;
    /**
     * 操作文件的物理位置
     */
    private String filePath;
}
