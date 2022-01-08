package com.cuit.common.model.base.file_manage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/1/8 11:52 AM
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StartOperationVo {
    /**
     * 用户的联系方式，执行完成操作之后向用户发送执行结果
     */
    @Email(message = "邮箱有误")
    private String contact;
    /**
     * 当前操作文件的路径
     */
    private String filePath;


}
