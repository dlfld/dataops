package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据集就是对应的一个文件夹，文件夹内有很多数据文件组成的集合
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/9 7:22 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataSet implements Serializable {
    /**
     * 数据集名字
     */
    private String dataSetName;
    /**
     * 数据集内文件数量
     */
    private Long dataSetFileCount;
    /**
     * 数据集上传者
     */
    private String dataSetUploader;
    /**
     * 数据集分享截止时间
     */
    private Date dataSetShareDeadline;
}
