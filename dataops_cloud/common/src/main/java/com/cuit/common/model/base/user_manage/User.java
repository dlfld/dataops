package com.cuit.common.model.base.user_manage;

import com.cuit.common.model.base.file_manage.ShareDataMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/8 5:05 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    /**
     * 用户名   唯一标识 尽量为实际名字简称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 实际名字
     */
    private String realName;
    /**
     * 分享出去的文件
     */
    private List<ShareDataMsg> shareDataMsgs = new ArrayList<>();

}
