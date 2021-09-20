package com.cuit.service_center.clients;

import com.cuit.common.pojo.base.PyClient;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description 这个实体类就是注册中心维护的客户端表
 * @Date 2021/9/20 2:40 下午
 * @Version 1.0
 */

public class ClientDataSet {
    /**
     * 客户端表
     */
    private static List<PyClient> clients = new ArrayList<>();

    /**
     * 添加一个客户端
     *
     * @param client 客户端
     */
    public static void addClient(PyClient client) {
        clients.add(client);
    }

    /**
     * 删除一个客户端
     * @param client 客户端
     */
    public static void removeClient(PyClient client) {
        
    }
}
