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
public class ShareProjectMsg implements Serializable {
    private static final long serialVersionUID = -5329013669211242515L;
    /**
     * 被分享者   --- 我分享给了谁-
     */
    public String userName;
    /**
     * 项目名
     */
    public String projectName;

}
