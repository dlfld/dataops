package com.cuit.common.model.base.file_manage.vo;

import com.cuit.common.model.base.file_manage.FileAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * description 用于接收文件分享的参数
 * @author wcw
 * @date 2021/11/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileShareInfoVo {
    /**
     * 分享者的名称
     */
    private String userName;

    /**
     * 被分享者的名称
     */
    private String shareName;

    /**
     * 文件的全路径
     */
    private String fileFullPath;

    /**
     * 文件分享属性列表
     */
    private List<FileAttribute> fileShareAttributeList;

    /**
     * 文件是否可下载
     */
    private Boolean downloadable;

    /**
     * 文件截止时间
     */
    private Date fileShareDeadLine;

    /**
     * 文件分享行数区间
     */
    private Integer start;
    private Integer end;
}
