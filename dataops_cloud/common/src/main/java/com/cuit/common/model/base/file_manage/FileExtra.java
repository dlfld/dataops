package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:26 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileExtra implements Serializable {

    private static final long serialVersionUID = -1591645344230775847L;
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 分享的截止时间
     */
    private Date deadline;
}
