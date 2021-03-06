package com.cuit.common.model.base.file_manage;

import com.cuit.common.model.base.file_manage.bo.OperationQueue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class DataFile implements Serializable {
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
     * 操作队列，提交之前的队列
     */
    private OperationQueue beforeOperationQueue;

    /**
     * 操作队列，执行之后的队列
     */
    private OperationQueue afterOperationQueue;

    /**
     * 文件位置
     */
    String fileLocate;
}
