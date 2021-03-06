package com.cuit.common.model.base.user_manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/17 5:01 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("用户注册")
public class UserRegisterVo implements Serializable {
    /**
     * 用户名   唯一标识 尽量为实际名字简称
     */
    @ApiModelProperty(value = "用户名   唯一标识 尽量为实际名字简称")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 实际名字
     */
    @ApiModelProperty(value = "实际名字")
    private String realName;
}
