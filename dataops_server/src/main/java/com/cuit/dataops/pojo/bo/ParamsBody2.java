package com.cuit.dataops.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 第二个版本的参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式调用开启
public class ParamsBody2 {
    List<Param> params;

    /**
     * 参数的添加方法
     * 这个方法用在结果集的添加中
     *          具体使用场景：  当这个类的实例作为返回结果的时候一个option的返回值
     *                          这个时候要把结果更新到结果集当中
     *
     * @param paramsBody2
     */
    public void add(ParamsBody2 paramsBody2){

    }
}
