package com.cuit.file_manage.operation.intf;

import com.cuit.common.model.base.file_manage.bo.OperationBo;
import com.cuit.common.model.base.file_manage.bo.OperationQueue;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * @Author dailin feng
 * @Description 操作解析器，将操作对应到
 * @Date 2022/1/9 2:46 PM
 * @Version 1.0
 */
@Service
public interface OperationParser {
    /**
     * 解析操作，将一个操作解析为可以调用的操作
     * @param operationQueue 操作实体类的队列
     * @return 解析后的业务类队列
     */
    Queue<OperationBo> parserOperation(OperationQueue operationQueue);
}
