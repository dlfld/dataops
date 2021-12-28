package com.cuit.file_manage.service.impl;

import com.cuit.common.model.base.file_manage.FileFinalValue;
import com.cuit.common.model.base.file_manage.operation.Operation;
import com.cuit.common.model.base.file_manage.operation.impl.OperationQueueJDKImpl;
import com.cuit.common.model.base.file_manage.operation.intf.OperationQueue;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.FileContentOperationService;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:38 PM
 * @Version 1.0
 */
@Component
public class FileContentOperationServiceImpl implements FileContentOperationService {
    /**
     * 前端传过来一个操作，将这个操作存储到操作队列里面去
     * 如果有操作队列就存到操作队列，如果没有的话就创建一个操作队列
     * 操作队列存储在和所操作对象同一级的目录当中。是一个meta文件
     * <p>
     * 首先检查是否存在当前操作对象的meta文件，
     * 如果存在的话就读取出当前的操作队列
     * 如果不存在的话就创建一个meta文件，并将操作队列置为空
     *
     * @param operation 操作对象
     * @return
     */
    @Override
    public ResponseData addOperation(Operation operation) {
        //这个filepath是元数据文件的文件位置，现在要通过当前数据文件的位置找到在元数据文件的位置
        String metaFilePath = operation.getFilePath();
        /**
         * 根据文件的分隔符来对文件进行分割，分割之后取数组最后的一截
         * 代表的就是文件的全名（带有文件后缀的）
         */
        String[] split = metaFilePath.split(FileUtil.getPathSeparator());
        String fileFullName = split[split.length - 1];

        //通过文件名中是否含有.来判断这个文件是否有后缀
        if (fileFullName.contains(".")) {
            //如果有后缀的话就把后缀去掉再加上meta的后缀
            int lastIndex = fileFullName.lastIndexOf(".");
            // 去掉后缀的
            String fileName = fileFullName.substring(0, lastIndex + 1);
            //元数据文件全名
            String metaFileFullName = fileName+ FileFinalValue.fileSuffix;
            metaFilePath = metaFilePath.replace(fileFullName,metaFileFullName);
        } else {
            //如果没有后缀的话就直接加meta的后缀
            metaFilePath = metaFilePath+FileFinalValue.fileSuffix;
        }

        OperationQueue operationQueue = null;
        //operation.getFilePath() 这个文件位置返回的是数据文件的位置，现在要将这个数据文件替换为元文件
        File file = new File(metaFilePath);
        //如果元文件不存在的话就创建一个空的操作队列
        if (!file.exists()) {
            operationQueue = new OperationQueueJDKImpl();
        } else {
            //如果不为空的话就读取元文件的操作队列
            operationQueue = MetaFileUtil.metaRead(metaFilePath,OperationQueueJDKImpl.class);
        }
        //入队
        if (operationQueue != null) {
            operationQueue.offer(operation);
        }
        return ResponseDataUtil.buildSuccess();
    }
}
