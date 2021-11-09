package com.cuit.scheduling.dispatch.utils;


import com.cuit.common.model.base.Param;
import com.cuit.common.model.base.ParamsBody2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个工具类是用来处理数据相关的工具类
 *
 * @author dailinfeng
 */
@Slf4j
@Component
public class ParamsUtils {

    /**
     * 更新参数的工具类
     * 判断参数是根据参数的desc来的
     * 1。根据传进来参数的版本号 高版本号的参数将覆盖低版本号的参数
     * 2。没有的参数将添加进去
     * <p>
     * 具体实现逻辑
     * 先判断两个参数是不是为空  有一个为空就返回另一个 （如果两个为空的情况？没有考虑 不可能除非上层代码出了问题）
     * 如果都不为空就开始参数的更新
     * 先把新参数遍历一遍 生成一个参数map  key为参数的形容（desc）  value是参数对象Param
     * 遍历老参数的list根据老参数的desc检索到新的参数  对比他们的版本号，如果新参数的版本号大于老参数的版本号就用新参数更新老参数的值更新
     * 在map中删除当前新参数
     * 如果到最后参数map中还有新参数的话 就说明相比于老参数列表 新参数列表添加了新的参数项
     * 把新的参数项更新到老参数列表之中
     *
     * @param source 传入的旧版本参数
     * @param target 传入的新版本参数，对这个版本的参数更新后返回
     * @return 更新之后的参数对象 ParamsBody2
     */
    public static ParamsBody2 refreshParams(ParamsBody2 source, ParamsBody2 target) {

        //如果旧版本的参数为空的话（就表示旧版本没有参数） 那就直接返回新版本的参数
        if (source == null || source.getItems() == null || source.getItems().isEmpty()) {
            log.info("传入的旧参数为空");
            return target;
        }
        //如果传入的新参数为空的话
        if (target == null || target.getItems() == null || target.getItems().isEmpty()) {
            log.info("传入的新参数为空");
            return source;
        }
        //如果都不为空的话就更新参数
        /**
         * 下面这几步做的功能是把新传进来的参数通过遍历一遍的方式转化为hashmap
         * 因为后面要很多遍遍历检索，因此通过一次遍历转化为map来加快速度
         */
        List<Param> params = source.getItems();
        Map<String, Param> sourceMap = new HashMap<>();
        for (Param param : params) {
            //按照设计思路来讲  这个params列表里面的每一个对象的desc是不可能重复的
            //重复了就是有bug
            sourceMap.put(param.getCurNodeId(), param);
        }
        //下面尽心参数的合并
        for (Param param : target.getItems()) {
            Param newParam = sourceMap.get(param.getCurNodeId());
            if (newParam == null) {
                continue;
            }
            //如果当前参数的版本号小于新传入的参数的版本号就更新参数
            //更新参数之后在map中删除更新的参数
            if (param.getVersion() < newParam.getVersion()) {
                param.setLocation(newParam.getLocation());
            }
            sourceMap.remove(newParam.getCurNodeId());
        }
        //如果新的参数的map已经为空（被删完了）
        if (sourceMap.isEmpty()) {
            return target;
        }
        log.info("加入了新的参数");
        //如果新的参数的map还没被删完  表示新增加了参数项  遍历新的参数项 添加到返回列表中
        for (Param param : sourceMap.values()) {
            target.getItems().add(param);
        }
        return target;
    }
}
