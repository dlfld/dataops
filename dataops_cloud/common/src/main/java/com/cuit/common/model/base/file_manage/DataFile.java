package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description 数据文件
 * @Date 2021/11/9 10:57 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DataFile {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件上传者
     */
    private String fileUploader;
    /**
     * 文件长度
     */
    private BigDecimal fileLength;
    /**
     * 文件属性对象列表
     */
    private FileAttribute fileAttribute;
    /**
     * 文件分享者
     */
    private String fileSharer;
    /**
     * 文件分享截止时间
     */
    private Date fileShareDeadline;
    /**
     * 文件分享行数
     */
    private BigDecimal fileSharelength;
    /**
     *文件分享属性列表
     */
    private List<FileAttribute> fileShareAttributeList = new ArrayList<>();


}
