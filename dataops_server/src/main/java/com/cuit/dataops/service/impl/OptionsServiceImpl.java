package com.cuit.dataops.service.impl;

import com.cuit.dataops.pojo.Connection;
import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.Option;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.rpc.Pyservice;
import com.cuit.dataops.service.OptionsService;
import com.cuit.dataops.utils.ResponseDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;


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
            if(!idQueue.isEmpty()){
                String cur = idQueue.element();
                if(StringUtils.equals(cur,node.getId())){
                    queue.offer(node);
                    idQueue.poll();
                }
            }
        }
        while (!queue.isEmpty()){
            Node node = queue.poll();
            res = Integer.parseInt(pyservice.callFunctionDemo(node.getOptUrl()+res));
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
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(StringUtils.equals(node.getId(),"1") || StringUtils.equals(node.getId(),"2")){
                continue;
            }
            res = Integer.parseInt(pyservice.callFunctionDemo(node.getOptUrl()+res));
        }
        return ResponseDataUtil.buildSuccess(res);

    }
}
