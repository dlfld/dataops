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

    private String fileName;

    private Date deadline;
}
