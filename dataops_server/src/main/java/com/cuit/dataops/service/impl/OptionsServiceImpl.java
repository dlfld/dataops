package com.cuit.dataops.service.impl;

import com.cuit.dataops.pojo.Connection;
import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.bo.Param;
import com.cuit.dataops.pojo.bo.ParamsBody;
import com.cuit.dataops.pojo.bo.ParamsBody2;
import com.cuit.dataops.pojo.bo.Task;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.dispatch.rpc.Pyservice;
import com.cuit.dataops.dispatch.rpc.RpcImpl;
import com.cuit.dataops.service.intf.OptionsService;
import com.cuit.dataops.dispatch.utils.DataUtils;
import com.cuit.dataops.utils.ResponseDataUtil;
import com.cuit.dataops.dispatch.taskFactory.impl.TaskFactoryStaticImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OptionsServiceImpl implements OptionsService {
    @Resource
    Pyservice pyservice;
    @Resource
    RpcImpl rpc;
    @Resource
    TaskFactoryStaticImpl taskFactoryStaticImpl;
//    static Logger logger = LoggerFactory.getLogger(OptionsServiceImpl.class);

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
        /**
         * 调度的操作步骤
         */
        for (Node node : sortList) {
            List<Object> inputParams = params.get(node.getId());
            Object res = "";
            //如果当前节点的url不为空  -> 为空的话表示当前节点是开始节点，开始节点不需要调用远程服务，
            //但是需要执行后面的操作： 封装他的子节点的参数列表
            if (StringUtils.isNotEmpty(node.getOptUrl())) {
                ParamsBody paramsBody = new ParamsBody(inputParams);
//                System.out.println(paramsBody);
                res = (String) rpc.httpRpc(node.getOptUrl(), paramsBody);
//                res = pyservice.callFunction(node.getOptUrl(), paramsBody);
            } else {
                //如果为空就什么都不做
//                res = params.get(node.getId()).stream()；
            }
            //如果连接表里面有当前节点
            if (connectionMap.containsKey(node.getId())) {
                List<Object> childrens = connectionMap.get(node.getId());
                //调度结果进行子节点参数封装
                for (Object childrenId : childrens) {
                    // add parameters to input of children node
                    if (params.containsKey(childrenId)) {
                        params.get(childrenId).add(res + "");
                    } else {
                        ArrayList<Object> temp = new ArrayList<Object>();
                        temp.add(res);
                        params.put(String.valueOf(childrenId), temp);
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
        //参数map的封装
        Map<String, List<Param>> params = new HashMap<String, List<Param>>() {{
            put("1", new ArrayList<Param>() {{
                        add(new Param().setDesc("start desc")
                                .setVersion(0)
                                .setObject("start obj"));
                    }}
            );
        }};
        //前端进行拓扑排序之后传过来的排序之后的序列
        List<Node> sortList = submitOptionsRequest.getNodes();
        //连接map  key是连接发出节点的id  value是指向节点的节点id 的列表
        Map<String, List<String>> connectionMap = new HashMap<>();
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
                connectionMap.put(connection.getSource().getId(), new ArrayList<String>() {{
                    add(connection.getDestination().getId());
                }});
            }
        }
        //结果列表
        List<ParamsBody2> resList = new ArrayList<>();
        log.info(sortList + "->sortList");
        //下面进行参数的封装和调度
        for (Node node : sortList) {
            //获取当前option的输入参数
            List<Param> inputParam = params.get(node.getId());
            //构建结果集
//            List<Param> res = new ArrayList<>();
            ParamsBody2 res = new ParamsBody2();
            //如果当前节点的url不为空  -> 为空的话表示当前节点是开始节点，开始节点不需要调用远程服务，
            //但是需要执行后面的操作： 封装他的子节点的参数列表
            if (StringUtils.isNotEmpty(node.getOptUrl())) {
                //获取调用的参数并封装
                ParamsBody2 paramsBody2 = new ParamsBody2(inputParam);
                //远程调用并返回结果
                res = rpc.httpRpcV2(node.getOptUrl(), paramsBody2);
            }
            log.info(connectionMap + "->connectionMap");
            //如果连接表里面有当前节点
            if (connectionMap.containsKey(node.getId())) {
                //获取当前节点的所有子节点
                List<String> childrenIds = connectionMap.get(node.getId());
                //遍历当前节点的子节点列表  给他们的参数上加上当前节点运算的返回值
                for (String childrenId : childrenIds) {
                    //如果这个子节点已经有了参数列表了，就往参数列表中添加（更新）现在的参数  -> 目前来看应该是更新操作
                    if (params.containsKey(childrenId)) {
                        ParamsBody2 target = new ParamsBody2(params.get(childrenId));
                        //更新参数
                        params.put(childrenId, DataUtils.refreshParams(res, target).getItems());
                    } else {
                        //如果这个节点是开始节点的后一个节点，就把开始节点的参数赋值给当前节点
                        if (StringUtils.isEmpty(node.getOptUrl())) {
                            params.put(childrenId, params.get("1"));
                        } else {
                            //说明之前还没有参数 现在直接加进去
                            params.put(childrenId, res.getItems());
                        }

                    }
                }
                //如果当前节点只有一个子节点，并且这个子节点的id为2的话表示当前节点是最后一个节点，
                // 应该把当前节点的返回值封装到结果返回类里面去
                if (childrenIds.size() == 1 && StringUtils.equals(String.valueOf(childrenIds.get(0)), "2")) {
                    resList.add(res);
                }
            }
        }
//        //删除掉返回之后多余的/ 和"
//        List<String> collect = resList.stream().map(item ->
//                String.valueOf(item).replace("\\", "").replace("\"", "")
//        ).collect(Collectors.toList());
        return ResponseDataUtil.buildSuccess(resList);
    }

    /**
     * 这个版本是抽取出task层
     *
     * @param submitOptionsRequest
     * @return
     */
    @Override
    public ResponseData runTopoOptionsTaskMode(SubmitOptionsRequest submitOptionsRequest) {
        //获取当前请求的task
        Task task = DataUtils.buildTask(submitOptionsRequest);
        //将task添加到task队列
        taskFactoryStaticImpl.offer(task);
        log.info(task + "-> 执行结果");
        //下面是遍历task的node队列进行参数更新和调度
        //返回成功
        return ResponseDataUtil.buildSuccess("成功加入队列");
    }
}
