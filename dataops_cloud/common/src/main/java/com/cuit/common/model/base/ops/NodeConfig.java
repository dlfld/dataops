package com.cuit.common.model.base.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author dailinfeng
 * @Description 配置类，前端修改的一些配置信息都存在这个类里面，到时候传到计算端
 * @Date 2021/8/22 3:41 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NodeConfig {
    /**
     * 是否删除这一步的结果保存
     * 如果不保存的话最后一步的时候会直接删除这一步中间结果
     * 莫认是不保存
     */
    private boolean saveResult = false;
}
