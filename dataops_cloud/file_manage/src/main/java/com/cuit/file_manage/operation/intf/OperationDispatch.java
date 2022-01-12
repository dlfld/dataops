package com.cuit.file_manage.operation.intf;

import com.cuit.common.model.base.file_manage.bo.OperationBo;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * @Author dailinfeng 分发
 * @Description TODO
 * @Date 2022/1/9 2:42 PM
 * @Version 1.0
 */
@Service
public interface OperationDispatch {
    /**
     * 调度器的调度方法
     * @param operationQueue 操作队列
     * @param userContact  用户的联系方式，向用户通知文件处理结果
     * @return 调度是否成功
     */
    boolean dispatchOperation(Queue<OperationBo> operationQueue,String userContact);


}
