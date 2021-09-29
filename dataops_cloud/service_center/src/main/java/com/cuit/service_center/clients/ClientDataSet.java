package com.cuit.service_center.clients;

import com.cuit.common.pojo.base.Option;
import com.cuit.common.pojo.base.PyClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author dailinfeng
 * @Description 这个实体类就是注册中心维护的客户端表
 * @Date 2021/9/20 2:40 下午
 * 第一个版本的注册中心，心跳是注册中心向客户端进行发送
 * @Version 1.0
 */
@Slf4j
public class ClientDataSet {
    /**
     * 客户端表
     */
    protected static List<PyClient> clients = new ArrayList<>();

    /**
     * 获取所有的客户端
     *
     * @return
     */
    public static List<PyClient> getClients() {
        return clients;
    }

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
//        array list 是线程不安全的 如果用多线程对arraylist进行使用的话会出现问题
        clients.forEach(pyClient -> {
            options.addAll(pyClient.getOptions());
        });
        return options;
    }

    /**
     * 检测一个客户端是否已经在表中
     *
     * @param pyClient
     * @return
     */
    public static Boolean exists(PyClient pyClient) {
        return clients.contains(pyClient);
    }

    /**
     * 判断当前提交的模块信息和以前的是否一样,如果一样就不更新，如果不一样就更新
     *
     * @param pyClient
     * @return
     */
    public static void updateIfChangeed(PyClient pyClient) {
        clients.parallelStream().forEach(item -> {
            //在表中找到当前的客户端
            if (Objects.equals(item.getIp(), pyClient.getIp()) && Objects.equals(pyClient.getPort(), item.getPort())) {
                boolean listEqual = isListEqual(item.getOptions(), pyClient.getOptions());
                //如果维护的模块表变了 就更新进去，没变就不管他 只是单纯的更新时间戳
                if (!listEqual) {
                    item.setOptions(pyClient.getOptions());
                }
                Long currentTimeMillis = System.currentTimeMillis();
                item.setLastHeartbeat(currentTimeMillis);
//                log.info("当前心跳时间{}", new Date(item.getLastHeartbeat()));
            }
        });
    }

    /**
     * 判断两个list是否相等
     *
     * @param list1
     * @param list2
     * @return bool
     */
    protected static boolean isListEqual(List<Option> list1, List<Option> list2) {
        //两个list引用相同（包括两者都为空的情况）
        if (list1 == list2) {
            return true;
        }
        // 两个list都为空（包括空指针、元素个数为0）
        boolean condition1 = list1 == null && list2 != null && list2.size() == 0;
        boolean condition2 = list2 == null && list1 != null && list1.size() == 0;
        if (condition1 || condition2) {
            return true;
        }
        /* 两个list元素个数不相同 */
        if (list1.size() != list2.size()) {
            return false;
        }

        // 两个list元素个数已经相同，再比较两者内容
        // 采用这种可以忽略list中的元素的顺序
        // 涉及到对象的比较是否相同时，确保实现了equals()方法
        if (!list1.containsAll(list2)) {
            return false;
        }
        return true;
    }
}
