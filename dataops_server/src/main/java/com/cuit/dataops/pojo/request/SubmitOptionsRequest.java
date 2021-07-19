package com.cuit.dataops.pojo.request;

import com.cuit.dataops.pojo.Connection;
import com.cuit.dataops.pojo.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author dailinfeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SubmitOptionsRequest {
    /**
     * 节点列表
     */
    private List<Node> nodes;
    /**
     * 链接列表
     */
    private List<Connection> connections;
    /**
     * 用户的联系方式  目前为止是用户的QQ
     */
    private String userContact;
    /**
     * 数据文件的文件名
     */
    private String dataFileName;
}
