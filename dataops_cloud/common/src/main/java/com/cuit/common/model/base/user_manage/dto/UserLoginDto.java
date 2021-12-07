package com.cuit.common.model.base.user_manage.dto;

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
 * @Date 2021/11/10 9:09 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("用户登录DTOO")
public class UserLoginDto implements Serializable {
    private static final long serialVersionUID = 6321070633974664333L;
    /**
     * 用户名   唯一标识 尽量为实际名字简称
     */
    @ApiModelProperty(value = "用户名   唯一标识 尽量为实际名字简称")
    private String userName;
    /**
     * 实际名字
     */
    @ApiModelProperty(value = "实际名字")
    private String realName;
    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;
}
