package com.cuit.file_manage.service.impl;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.file_manage.DataFile;
import com.cuit.common.model.base.file_manage.FileFinalValue;
import com.cuit.common.model.base.file_manage.vo.OperationVo;
import com.cuit.common.model.base.file_manage.vo.StartOperationVo;
import com.cuit.common.utils.IdWorker;
import com.cuit.file_manage.convert.OperationConvert;
import com.cuit.file_manage.operation.impl.OperationQueueJDKImpl;
import com.cuit.common.model.base.file_manage.bo.OperationQueue;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.operation.intf.OperationQueueRunner;
import com.cuit.file_manage.service.intf.FileContentOperationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.io.File;
import java.util.Objects;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:38 PM
 * @Version 1.0
 */
@Component
public class FileContentOperationServiceImpl implements FileContentOperationService {
    @Resource
    IdWorker idWorker;
    @Resource
    OperationQueueRunner operationQueueRunner;

    /**
     * 前端传过来一个操作，将这个操作存储到操作队列里面去
     * 如果有操作队列就存到操作队列，如果没有的话就创建一个操作队列
     * 操作队列存储在和所操作对象同一级的目录当中。是一个meta文件
     * <p>
     * 首先检查是否存在当前操作对象的meta文件，
     * 如果存在的话就读取出当前的操作队列
     * 如果不存在的话就创建一个meta文件，并将操作队列置为空
     *
     * @param operationVo 操作对象
     * @return 返回的是将当前操作添加到操作队列之后对当前操作生成的操作id
     */
    @Override
    public ResponseData addOperation(OperationVo operationVo) {
        //这个filepath是元数据文件的文件位置，现在要通过当前数据文件的位置找到在元数据文件的位置
        String metaFilePath = operationVo.getFilePath();
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
            String fileName = fileFullName.substring(0, lastIndex);
            //元数据文件全名
            String metaFileFullName = fileName + FileFinalValue.fileSuffix;
            metaFilePath = metaFilePath.replace(fileFullName, metaFileFullName);
        } else {
            //如果没有后缀的话就直接加meta的后缀
            metaFilePath = metaFilePath + FileFinalValue.fileSuffix;
        }

        OperationQueue operationQueue = null;
        File file = new File(metaFilePath);
        DataFile dataFile = null;
        //如果元文件不存在的话就创建一个空的操作队列
        if (!file.exists()) {
            dataFile = new DataFile();
            dataFile.setFileLocate(operationVo.getFilePath());
            operationQueue = new OperationQueueJDKImpl();
            dataFile.setBeforeOperationQueue(operationQueue);

        } else {
            dataFile = MetaFileUtil.metaRead(metaFilePath, DataFile.class);
            //如果不为空的话就读取元文件的操作队列
            operationQueue = dataFile.getBeforeOperationQueue();
        }

        //设置当前操作的id
        operationVo.setId(idWorker.nextId() + "");
        //入队
        if (operationQueue != null) {
            operationQueue.offer(OperationConvert.voToPojo(operationVo));
        }
        dataFile.setBeforeOperationQueue(operationQueue);
        MetaFileUtil.metaWrite(metaFilePath, dataFile);
        return ResponseDataUtil.buildSuccess(operationVo.getId());
    }

    /**
     * 执行操作的撤回操作，传过来的是当前操作文件的路径
     * 操作步骤
     * 首先判断当前的所操作的文件是否存在
     * 如果存在的话判断当前文件对应的操作队列元数据文件是否存在
     * 如果存在的话判断队列元文件里是否有操作
     * 如果有操作的话删除队列尾部的元素
     *
     * @param filePath 当前操作文件的路径
     * @return 返回操作撤回是否成功
     */
    @Override
    public ResponseData recallOperation(String filePath) {
        File operationFile = new File(filePath);
        //如果文件不存在的话就返回错误
        if (!operationFile.exists()) {
            return ResponseDataUtil.buildError(ResultEnums.FILE_NOT_FOUND);
        }
        //todo

        return null;
    }

    /**
     * 开始执行操作队列的方法
     * 首先判断操作的文件是否存在
     * 如果文件存在判断对应的执行队列是否存在
     * 如果执行队列存在则开始调度
     *
     * @param startOperationVo 开始的调度
     * @return 调度成功之后获取返回结果
     */
    @Override
    public ResponseData startOperation(StartOperationVo startOperationVo) {
        File file = new File(startOperationVo.getFilePath());
        //如果文件不存在的话就返回文件不存在
        if (!file.exists()) {
            return ResponseDataUtil.buildError(ResultEnums.FILE_NOT_FOUND);
        }
        // 获取meta文件的全路径
        String metaFilePath = MetaFileUtil.getMateFilePath(startOperationVo.getFilePath());
        File metaFile = new File(metaFilePath);
        if (!metaFile.exists()) {
            //如果元数据文件不存在则表示没有操作队列
            return ResponseDataUtil.buildError(ResultEnums.OPERATION_QUEUE_NOT_FOUND);
        }
        //获取操作队列
        DataFile dataFile = MetaFileUtil.metaRead(metaFilePath, DataFile.class);
        //开始运行
        boolean res = operationQueueRunner.runOperationQueue(dataFile.getBeforeOperationQueue(), startOperationVo.getContact());
        if (res) {
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }
}
