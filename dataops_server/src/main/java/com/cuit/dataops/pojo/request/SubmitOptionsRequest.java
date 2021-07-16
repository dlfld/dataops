package com.cuit.dataops.pojo.request;

import com.cuit.dataops.pojo.Connection;
import com.cuit.dataops.pojo.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class SubmitOptionsRequest {
    private List<Node> nodes;  //节点列表
    private List<Connection> connections; //链接列表
    //    private int baseNumber;
    private String userContact;//用户的联系方式  目前为止是用户的QQ
}
