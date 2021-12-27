package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件夹 
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/9 7:22 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Folder implements Serializable {
    private static final long serialVersionUID = -1974023055805742280L;
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
