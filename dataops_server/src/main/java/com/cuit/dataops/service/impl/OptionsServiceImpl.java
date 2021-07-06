package com.cuit.dataops.service.impl;

import com.cuit.dataops.pojo.Connection;
import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.Option;
import com.cuit.dataops.pojo.bo.ParamsBody;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.rpc.Pyservice;
import com.cuit.dataops.service.OptionsService;
import com.cuit.dataops.utils.ResponseDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


@Component
public class OptionsServiceImpl implements OptionsService {
    @Resource
    Pyservice pyservice;


    @Override
    public ResponseData getOptions() {
        return ResponseDataUtil.buildSuccess(pyservice.getOptions());
    }

    //    @Override
    public ResponseData runOptions_old(SubmitOptionsRequest submitOptionsRequest) {
        int res = submitOptionsRequest.getBaseNumber();
        String current = "1";
        //option调用队列
        Queue<Node> queue = new LinkedList<Node>();
        Queue<String> idQueue = new LinkedList<String>();
        for (Node node : submitOptionsRequest.getNodes()) {
            for (Connection connection : submitOptionsRequest.getConnections()) {
                /**如果检索到的源头id是当前id
                 *    再判断当前的下一个是不是尾巴节点
                 *          不是就把当前的下一个加入队列
                 */
                if (StringUtils.equals(connection.getSource().getId(), current)) {

                    current = connection.getDestination().getId();
                    if (!StringUtils.equals(connection.getDestination().getId(), "2")) {
                        idQueue.offer(connection.getDestination().getId());
                    }
                }
            }
        }
        System.out.println(idQueue.isEmpty());
        for (Node node : submitOptionsRequest.getNodes()) {
            if (!idQueue.isEmpty()) {
                String cur = idQueue.element();
                if (StringUtils.equals(cur, node.getId())) {
                    queue.offer(node);
                    idQueue.poll();
                }
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            res = Integer.parseInt(pyservice.callFunctionDemo(node.getOptUrl() + res));
        }
        System.out.println(res);
        return ResponseDataUtil.buildSuccess(res);
    }


    @Override
    public ResponseData runOptions(SubmitOptionsRequest submitOptionsRequest) {

        return null;
    }

    @Override
    public ResponseData runTopoOptions(List<Node> nodes) {
        Queue<Node> queue = new LinkedList<Node>();
        nodes.forEach(queue::offer);
        int res = 10;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (StringUtils.equals(node.getId(), "1") || StringUtils.equals(node.getId(), "2")) {
                continue;
            }
            res = Integer.parseInt(pyservice.callFunctionDemo(node.getOptUrl() + res));
        }
        return ResponseDataUtil.buildSuccess(res);

    }


    @Override
    public ResponseData runTopoOptions3(SubmitOptionsRequest submitOptionsRequest) {
        //参数map
        Map<String, List<String>> params = new HashMap<String, List<String>>() {{
            //初始化第一个节点
            put("1", new ArrayList<String>() {{
                add("input1");
            }});
        }};
        List<Node> sortList = submitOptionsRequest.getNodes();
        Map<String, List<String>> connectionMap = new HashMap<>();

        // 构建connectinoMap
        List<Connection> connections = submitOptionsRequest.getConnections();
        for (Connection connection : connections) {
            boolean hasKey = connectionMap.containsKey(connection.getSource().getId());
            if (hasKey) {
                connectionMap.get(connection.getSource().getId()).add(connection.getDestination().getId());
            } else {
                connectionMap.put(connection.getSource().getId(), new ArrayList<String>() {{
                    add(connection.getDestination().getId());
                }});
            }
        }
        List<String> resultRes = new ArrayList<>();
        // 调度
        for (Node node : sortList) {
//            if(StringUtils.equals(node.getId(),"1")){
//
//                continue;
//            }

            List<String> input_params = params.get(node.getId());
//            System.out.println(node.getId());
            String res = "start";
            System.out.println(node);
            if (StringUtils.isNotEmpty(node.getOptUrl())) {

                ParamsBody paramsBody = new ParamsBody(input_params);
                System.out.println(paramsBody);
                res = pyservice.callFunction(node.getOptUrl(), paramsBody);
            }

//            int res = 1;
            if (connectionMap.containsKey(node.getId())) {
                List<String> childrens = connectionMap.get(node.getId());
                //调度结果进行子节点参数封装
                for (String children_id : childrens) {
                    // add parameters to input of children node
                    if (params.containsKey(children_id)) {
                        params.get(children_id).add(res + "");
                    } else {
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(res);
                        params.put(children_id, temp);
                    }
                }
                if(childrens.size()==1&&StringUtils.equals(childrens.get(0),"2")){
                    System.out.println(childrens);
                    resultRes.add(res);
                }
            }

//            params.remove(node.getId());
        }
        return ResponseDataUtil.buildSuccess(resultRes);
    }
}
