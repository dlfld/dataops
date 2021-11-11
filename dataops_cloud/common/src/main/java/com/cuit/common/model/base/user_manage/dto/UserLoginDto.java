package com.cuit.common.model.base.user_manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class UserLoginDto {
    /**
     * 用户名   唯一标识 尽量为实际名字简称
     */
    private String userName;
    /**
     * 实际名字
     */
    private String realName;
    /**
     * token
     */
    private String token;
}
