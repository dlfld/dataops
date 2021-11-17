package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author dailinfeng
 * @Description 我分享出去的文件 文件信息
 * @Date 2021/11/15 4:21 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ShareDataMsg implements Serializable {
    /**
     * 被分享者   --- 我分享给了谁
     */
    public String userName;
    /**
     * 文件名
     */
    public String fileName;

}
