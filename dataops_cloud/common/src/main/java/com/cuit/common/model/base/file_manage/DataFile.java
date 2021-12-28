package com.cuit.common.model.base.file_manage;

import com.cuit.common.model.base.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class DataFile extends Meta implements Serializable {
    private static final long serialVersionUID = -3767675891296219019L;
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
    private Long fileLength;
    /**
     * 文件属性对象列表
     */
    private List<FileAttribute> fileAttributeList = new ArrayList<>();
    /**
     * 文件分享截止时间
     */
    private Date fileShareDeadline;

    /**
     * 文件分享行数区间
     */
    private Integer rowsStart;
    private Integer rowsEnd;
    /**
     * 文件分享属性列表
     */
    private List<FileAttribute> fileShareAttributeList = new ArrayList<>();
    /**
     * 文件是否可下载
     */
    private boolean downloadable = false;

    /**
     * 读meta文件
     * @param filePath meta文件的实际位置
     * @return
     */
    public DataFile metaRead(String filePath) {
        return super.metaRead(filePath, DataFile.class);
    }

}
