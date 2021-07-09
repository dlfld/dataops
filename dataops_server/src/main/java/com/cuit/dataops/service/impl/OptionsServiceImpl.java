package com.cuit.dataops.service.impl;

import com.cuit.dataops.pojo.Connection;
import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.bo.ParamsBody;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.rpc.Pyservice;
import com.cuit.dataops.rpc.RpcImpl;
import com.cuit.dataops.service.intf.OptionsService;
import com.cuit.dataops.utils.ResponseDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class OptionsServiceImpl implements OptionsService {
    @Resource
    Pyservice pyservice;
    @Resource
    RpcImpl rpc;

    @Override
    public ResponseData getOptions() {
        return ResponseDataUtil.buildSuccess(pyservice.getOptions());
    }


    @Override
    public ResponseData runTopoOptions3(SubmitOptionsRequest submitOptionsRequest) {
        //参数map
        Map<String, List<Object>> params = new HashMap<String, List<Object>>() {{
            //初始化第一个节点
            put("1", new ArrayList<Object>() {{
                add("input1");
            }});
        }};
        //前端进行拓扑排序之后传过来的排序之后的序列
        List<Node> sortList = submitOptionsRequest.getNodes();
        //连接map
        Map<String, List<Object>> connectionMap = new HashMap<>();


        /**
         * 构建connectinoMap
         *   这个map的key是连接发出节点的id  value是连接指向节点的id（列表->一个节点指向多个节点的情况）
         *   构建这个map的原因是因为调度的时候会多次对连接表进行查询，用map就会减少遍历次数
         */
        List<Connection> connections = submitOptionsRequest.getConnections();
        for (Connection connection : connections) {
            boolean hasKey = connectionMap.containsKey(connection.getSource().getId());
            if (hasKey) {
                connectionMap.get(connection.getSource().getId()).add(connection.getDestination().getId());
            } else {
                connectionMap.put(connection.getSource().getId(), new ArrayList<Object>() {{
                    add(connection.getDestination().getId());
                }});
            }
        }
        //结果列表
        List<Object> resultRes = new ArrayList<>();
        // 调度
        for (Node node : sortList) {
            List<Object> input_params = params.get(node.getId());
            Object res = "";
            //如果当前节点的url不为空  -> 为空的话表示当前节点是开始节点，开始节点不需要调用远程服务，
            //但是需要执行后面的操作： 封装他的子节点的参数列表
            if (StringUtils.isNotEmpty(node.getOptUrl())) {
                ParamsBody paramsBody = new ParamsBody(input_params);
//                System.out.println(paramsBody);
                res = (String)rpc.httpRpc(node.getOptUrl(), paramsBody);
//                res = pyservice.callFunction(node.getOptUrl(), paramsBody);
            }else{
//                res = params.get(node.getId()).stream()；
            }
            //如果连接表里面有当前节点
            if (connectionMap.containsKey(node.getId())) {
                List<Object> childrens = connectionMap.get(node.getId());
                //调度结果进行子节点参数封装
                for (Object children_id : childrens) {
                    // add parameters to input of children node
                    if (params.containsKey(children_id)) {
                        params.get(children_id).add(res + "");
                    } else {
                        ArrayList<Object> temp = new ArrayList<Object>();
                        temp.add(res);
                        params.put(String.valueOf(children_id), temp);
                    }
                }
                //如果当前节点只有一个子节点，并且这个子节点的id为2的话表示当前节点是最后一个节点，
                // 应该把当前节点的返回值封装到结果返回类里面去
                if (childrens.size() == 1 && StringUtils.equals(String.valueOf(childrens.get(0)), "2")) {
                    System.out.println(childrens);
                    resultRes.add(res);
                }
            }
            params.remove(node.getId());
        }
        //删除掉返回之后多余的/ 和"
        List<String> collect = resultRes.stream().map(item ->
                String.valueOf(item).replace("\\", "").replace("\"", "")
        ).collect(Collectors.toList());
        return ResponseDataUtil.buildSuccess(collect);
    }


    @Override
    public ResponseData runTopoOptionsMapMode(SubmitOptionsRequest submitOptionsRequest) {
        return null;
    }
}
