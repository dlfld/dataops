package com.cuit.dataops.service.intf;

import com.cuit.dataops.pojo.Node;
import com.cuit.dataops.pojo.request.SubmitOptionsRequest;
import com.cuit.dataops.pojo.response.ResponseData;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface OptionsService {
    //获取所有的option
    ResponseData getOptions();

    //执行options

    /**
     * 操作模式：
     *      前端传过来的是节点列表（node）和连接列表（connections）
     *      遍历连接列表，生成参数map这个map的key是连接发出节点的id，value是节点的后继节点的id列表（后继节点不止有一个）
     *      下一步就是调度
     *      首先是遍历排序好的node节点。
     *          参数map->维护了一个参数map  key是节点的id value是这个节点传入的参数列表（list）
     *             首先根据id获取这个节点的参数列表 并调用该节点进行计算，参数传的是该节点的参数列表
     *             再遍历当前调用节点的子节点
     *             根据这些子节点的id 向他们的参数列表中添加当前节点计算之后的返回结果
     *
     * @param submitOptionsRequest
     * @return
     */
    ResponseData runTopoOptions3(SubmitOptionsRequest submitOptionsRequest);

    /**
     * 与上一个方法不同的地方是：
     *          上一个方法的参数列表是使用list封装的 实际上option里并不知道自己需要操作的是那一些信息记录，
     *          因此这个版本的方法使用map来封装参数  map中key的生成是有一个key的生成规则（约定俗成的规范）的
     *          每次系统调度的时候传递所有的参数给option端，option根据自己需要的参数的key（key描述了这个参数的信息）可以获取到相应的参数
     *
     *          这个key的生成规则是什么？ 生成规则应该是唯一的，同一个参数只能生成一个确定的key
     *
     *
     * 操作步骤
     *      前端传过来的节点列表（node） 和连接列表（connections）
     *      遍历连接列表，生成参数map和这个map的key是连接发出点的id，value是节点的后继节点的id列表（后继节点不止有一个）
     *      下一步就是调度
     *
     *      调度算法构思：
     *          调度的时候封装的参数为map类型的
     *          结果返回的时候也是一个map类型
     *          Java端用map接收这个返回类型之后更新返回结果值。-> 出现问题  如果有多个map返回的话应该怎么办？
     *                                                              二合一的这种情况怎么合并？
     *                                                                      -> 如果是单一的根据key合并的话可能使得操作失效
     *                                                  -> 解决方法  还是得用list  只是list里面不是个单纯的存Obj而是存的一个对象，
     *                                                              这个对象里存的是对参数的描述和参数
     *                                                              一个参数操作完成之后再对数据进行封装和组合
     *
     *                          -> 新问题：  如果两个操作操作的是同一组数据  并且他们是汇聚的节点  返回的参数应该怎么合并的问题？ （总的来说）
     *                                      总的来说就是参数合并的问题。
     *                                      如果是两个节点合并为一个节点的情况 用map的时候会出现key重复然后value覆盖的情况
     *                                      如果用list的话 每一个参数都是一个对象也有可能出现一种参数在一个参数list里面出现多次并且值不同的情况
     *                          -> 解决方法： 建立版本号机制，操作对参数列表中的参数进行修改之后对版本号进行增加，参数返回以后由新版本号覆盖旧版本号
     *                                         进行参数的更新
     *
     *
     * @param submitOptionsRequest
     * @return
     */
    ResponseData runTopoOptionsMapMode(SubmitOptionsRequest submitOptionsRequest);
}
