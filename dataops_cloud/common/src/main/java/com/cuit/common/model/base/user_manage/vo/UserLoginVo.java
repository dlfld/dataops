package com.cuit.common.model.base.user_manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/10 9:39 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserLoginVo implements Serializable {
    private static final long serialVersionUID = -8514637483360990772L;
    /**
     * 用户名唯一标识尽量为实际名字简称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
