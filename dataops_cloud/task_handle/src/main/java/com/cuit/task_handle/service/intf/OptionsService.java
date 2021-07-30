package com.cuit.task_handle.service.intf;

import com.cuit.common.pojo.request.SubmitOptionsRequest;
import com.cuit.common.pojo.response.ResponseData;
import org.springframework.stereotype.Component;


/**
 * @author dailinfeng
 */
@Component
public interface OptionsService {
    /**
     * 获取所有的option
     * @return
     */
    ResponseData getOptions();

    //执行options

    /**
     * V1废弃⚠️
     * <p>
     * 操作模式：
     * 前端传过来的是节点列表（node）和连接列表（connections）
     * 遍历连接列表，生成参数map这个map的key是连接发出节点的id，value是节点的后继节点的id列表（后继节点不止有一个）
     * 下一步就是调度
     * 首先是遍历排序好的node节点。
     * 参数map->维护了一个参数map  key是节点的id value是这个节点传入的参数列表（list）
     * 首先根据id获取这个节点的参数列表 并调用该节点进行计算，参数传的是该节点的参数列表
     * 再遍历当前调用节点的子节点
     * 根据这些子节点的id 向他们的参数列表中添加当前节点计算之后的返回结果
     *
     * @param submitOptionsRequest
     * @return
     */
//    ResponseData runTopoOptions3(SubmitOptionsRequest submitOptionsRequest);

    /**
     * V2废弃⚠️
     * <p>
     * 与上一个方法不同的地方是：
     * 上一个方法的参数列表是使用list封装的Object 实际上option里并不知道自己需要操作的是那一些信息记录，
     * 这个版本使用list中的Param进行参数的多一层封装，Param中属性可以对该参数进行描述和版本号的控制
     * <p>
     * 这个key的生成规则是什么？ 生成规则应该是唯一的，同一个参数只能生成一个确定的key
     * <p>
     * <p>
     * 操作步骤
     * 前端传过来的节点列表（node） 和连接列表（connections）
     * 遍历连接列表，生成参数map和这个map的key是连接发出点的id，value是节点的后继节点的id列表（后继节点不止有一个）
     * 下一步就是调度
     * <p>
     * 调度算法构思：
     * 调度的时候封装的参数为map类型的
     * 结果返回的时候也是一个map类型
     * Java端用map接收这个返回类型之后更新返回结果值。-> 出现问题  如果有多个map返回的话应该怎么办？
     * 二合一的这种情况怎么合并？
     * -> 如果是单一的根据key合并的话可能使得操作失效
     * -> 解决方法  还是得用list  只是list里面不是个单纯的存Obj而是存的一个对象，
     * 这个对象里存的是对参数的描述和参数
     * 一个参数操作完成之后再对数据进行封装和组合
     * <p>
     * -> 新问题：  如果两个操作操作的是同一组数据  并且他们是汇聚的节点  返回的参数应该怎么合并的问题？ （总的来说）
     * 总的来说就是参数合并的问题。
     * 如果是两个节点合并为一个节点的情况 用map的时候会出现key重复然后value覆盖的情况
     * 如果用list的话 每一个参数都是一个对象也有可能出现一种参数在一个参数list里面出现多次并且值不同的情况
     * -> 解决方法： 建立版本号机制，操作对参数列表中的参数进行修改之后对版本号进行增加，参数返回以后由新版本号覆盖旧版本号
     * 进行参数的更新
     * 两个节点合并为一个节点，并且都是操作同一个param的时候他们的版本号都是加一 这个时候版本号相同 的情况
     *
     * @param submitOptionsRequest
     * @return
     */
//    ResponseData runTopoOptionsMapMode(SubmitOptionsRequest submitOptionsRequest);


    /**
     * V3
     * 这个版本是抽取出task层
     * task里封装参数表和node节点表，到时候调度就直接使用task进行调度
     * <p>
     * 在后续的调度过程中就只需要每次传入参数表  更新参数表即可
     * 最后直接返回给前端这个task都行  或者是再封装一下再返回
     *
     * @param submitOptionsRequest
     * @return
     */
    ResponseData runTopoOptionsTaskMode(SubmitOptionsRequest submitOptionsRequest);
}
