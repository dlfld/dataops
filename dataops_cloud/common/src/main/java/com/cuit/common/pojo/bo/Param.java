package com.cuit.common.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class Param implements Serializable {
    private String desc; //参数的描述  计算端根据参数的描述来进行参数的检索
    private Integer version = 0; //参数当前的版本号，后期根据版本号进行参数的更新
    private Object object;//参数体
}
