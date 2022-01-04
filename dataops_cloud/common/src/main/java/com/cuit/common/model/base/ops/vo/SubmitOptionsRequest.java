package com.cuit.common.model.base.ops.vo;

import com.cuit.common.model.base.ops.Connection;
import com.cuit.common.model.base.ops.Node;
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
    public List<Node> nodes;
    /**
     * 链接列表
     */
    public List<Connection> connections;
    /**
     * 用户的联系方式  目前为止是用户的QQ
     */
    public String userContact;
    /**
     * 数据文件的文件名
     */
    public String dataFileName;
    /**
     * 数据文件的全路径
     */
    public String dataFileFullPath;
    /**
     * 数据的描述
     */
    public String paramsDesc;
    /**
     * 文件下载链接
     */
    private String downloadUrl;
}
