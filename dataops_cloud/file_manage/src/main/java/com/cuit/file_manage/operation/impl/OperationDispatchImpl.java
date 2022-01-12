package com.cuit.file_manage.operation.impl;

import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.exception.OperationRunnerException;
import com.cuit.common.model.base.file_manage.DataFile;
import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.common.model.base.file_manage.bo.OperationBo;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.file_manage.operation.intf.OperationDispatch;
import org.springframework.stereotype.Service;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;

/**
 * @Author dailinfeng
 * @Description TODO 解析器
 * @Date 2022/1/9 2:43 PM
 * @Version 1.0
 */
@Service
public class OperationDispatchImpl implements OperationDispatch {
    /**
     * 调度器的调度方法
     * @param userContact 用户联系方式，用来向用户通知处理结果
     * @param operationQueue 操作队列
     * @return 调度是否成功
     */
    @Override
    public boolean dispatchOperation(Queue<OperationBo> operationQueue,String userContact) {
        while (!operationQueue.isEmpty()){
            OperationBo operationBo = operationQueue.poll();
            Method method = operationBo.getMethod();
            try {
                boolean achieve = (boolean)method.invoke(operationBo);
                operationBo.setAchieve(achieve);
                //如果当前操作完成了便在操作队列中出队当前元素,并将当前元素添加到执行之后的日志队列中
                if(achieve){
                    String metaFilePath = MetaFileUtil.getMateFilePath(operationBo.getFilePath());
                    DataFile dataFile = MetaFileUtil.metaRead(metaFilePath,DataFile.class);
                    //当前操作元素出队
                    Operation currRunOperation = dataFile.getBeforeOperationQueue().poll();
                    //如果操作日志队列为空的话就创建一个新的队列
                    if(dataFile.getAfterOperationQueue() == null){
                        dataFile.setAfterOperationQueue(new OperationQueueJDKImpl());
                    }
                    //向日志队列添加当前执行的操作
                    dataFile.getAfterOperationQueue().offer(currRunOperation);
                }else{
                    //如果是操作失败的话停止当前的运行
                    ExceptionCast.customDefineCast(new OperationRunnerException(userContact));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
