package com.cuit.file_manage.convert;

import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.common.model.base.file_manage.vo.OperationVo;
import org.springframework.beans.BeanUtils;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/1/4 9:57 PM
 * @Version 1.0
 */
public class OperationConvert {
    /**
     * 实体类属性转换
     * @param operationVo 页面传过来的对象
     * @return Operation对象
     */
    public static Operation voToPojo(OperationVo operationVo){
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationVo,operation);
        return operation;
    }
}
