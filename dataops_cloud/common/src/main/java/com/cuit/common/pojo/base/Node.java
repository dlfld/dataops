package com.cuit.common.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class Node implements Serializable {
    private String id;  //前端组件的id
    private String optUrl;//请求python端的url
}
