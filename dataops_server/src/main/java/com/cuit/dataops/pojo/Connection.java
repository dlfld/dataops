package com.cuit.dataops.pojo;

import com.cuit.dataops.pojo.bo.NodeMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class Connection {
    private NodeMsg source; //出发点
    private NodeMsg destination; //指向点
}
