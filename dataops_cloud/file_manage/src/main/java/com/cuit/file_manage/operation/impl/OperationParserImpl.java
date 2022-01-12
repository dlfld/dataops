package com.cuit.file_manage.operation.impl;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.common.model.base.file_manage.bo.OperationBo;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.convert.OperationConvert;
import com.cuit.file_manage.operation.factory.FileFactory;
import com.cuit.file_manage.operation.handler.AbstractFileHandler;
import com.cuit.file_manage.operation.intf.OperationParser;
import com.cuit.common.model.base.file_manage.bo.OperationQueue;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author dailinfeng
 * @Description 操作解析类，解析操作所对应的执行类和执行方法
 * @Date 2022/1/9 2:50 PM
 * @Version 1.0
 */
@Component
public class OperationParserImpl implements OperationParser {

    /**
     * 解析操作，将一个操作解析为可以调用的操作
     * 解析文件后缀，找到对应处理类
     * 根据对应的处理类找到对应处理类里面的方法，并将方法封装到Bo当中
     *
     * @param operationQueue 操作队列
     * @return 解析之后的实体类
     */
    @Override
    public Queue<OperationBo> parserOperation(OperationQueue operationQueue) {
        Queue<OperationBo> parseOperationBoQueue = new LinkedList<>();
        while (operationQueue.isNotEmpty()) {
            //获取队首元素
            Operation operation = operationQueue.poll();
            //获取文件后缀的位置
            int lastIndexOf = operation.getFilePath().lastIndexOf(".");
            //截取文件后缀
            String fileType = operation.getFilePath().substring(lastIndexOf);
            //获取文件处理的handler
            AbstractFileHandler fileHandler = FileFactory.getInvokeStrategy(fileType);
            OperationBo operationBo = OperationConvert.pojoToBo(operation);
            operationBo.setFileType(fileType);
            //获取到的具体实现类
            Class<? extends AbstractFileHandler> handlerClass = fileHandler.getClass();
            try {
                Method method = handlerClass.getMethod(operation.getOperation(), OperationBo.class);
                operationBo.setMethod(method);
            } catch (NoSuchMethodException e) {
                //如果操作找不到的话就报错找不到当前操作
                ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.OPERATION_NOT_FOUND));
            }
            //解析之后进行入队操作
            parseOperationBoQueue.offer(operationBo);
        }
        return parseOperationBoQueue;
    }
}
