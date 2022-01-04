package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
     * 全路径
     */
    @NotEmpty(message = "当前操作的文件不能为空")
    private String filePath;

    /**
     * 处理文件的行号列表,
     */
    private List<Long> rowNum;
    /**
     * 处理文件的列号列表
     */
    private List<Long> colNum;
    /**
     * 处理文件的操作的描述
     */
    @NotEmpty(message = "当前操作的文件不能为空")
    private String operation;

    /**
     * 当前操作的ID
     * 操作的唯一标识符
     */
    private String id;

}
