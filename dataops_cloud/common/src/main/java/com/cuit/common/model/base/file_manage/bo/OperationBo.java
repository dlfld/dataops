package com.cuit.common.model.base.file_manage.bo;

import com.cuit.common.model.base.file_manage.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/1/11 10:36 PM
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OperationBo <T> extends Operation {
    private static final long serialVersionUID = 4356222088751642262L;
    /**
     * 文件类型
     */
    String fileType;
    /**
     * 具体调用的方法
     */
    Method method;
    /**
     * 是否完成当前操作的调用
     */
    boolean achieve;

    /**
     * 构造方法，在调用方法的时候需要调用 实体类的构造方法
     */
    Constructor<T> constructor;
    
}
