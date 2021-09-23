package com.cuit.service_center.clients;

import com.cuit.common.pojo.base.Option;
import com.cuit.common.pojo.base.PyClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     *
     * @param client 客户端
     */
    public static void removeClient(PyClient client) {
        //如果IP和端口相同则删除
        clients.removeIf(next -> Objects.equals(next.getIp(), client.getIp()) && (Objects.equals(next.getPort(), client.getPort())));
    }

    /**
     * 更新一个客户端的模块信息
     *
     * @param client 客户端
     */
    public static void updateClient(PyClient client) {
        clients.parallelStream().forEach(item -> {
            if (Objects.equals(item.getIp(), client.getIp()) && (Objects.equals(item.getPort(), client.getPort()))) {
                item.setOptions(client.getOptions());
            }
        });
    }

    /**
     * 获取客户端列表
     *
     * @return 模块信息列表
     */
    public static List<Option> getOptions() {
        List<Option> options = new ArrayList<>();
        clients.stream().forEach(pyClient -> {
            options.addAll(pyClient.getOptions());
        });
        return options;
    }
}
