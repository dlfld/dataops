package com.cuit.common.model.base.file_manage;

import com.cuit.common.model.base.user_manage.User;
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
 * @Description TODO
 * @Date 2021/12/23 7:02 PM
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Project implements Serializable {

    private static final long serialVersionUID = -6881704242325315231L;

    /**
     * 项目名
     */
    private String projectName;


    /**
     * 项目分享者列表
     */
    private List<String>  userShareList = new ArrayList<>();


    /**
     * 项目创建者
     */
    private String userName;


    /**
     * 项目创建日期
     */
    private Date createDate;

    /**
     * 项目分享的截止时间
     */
    private Date fileShareDeadline;
}
