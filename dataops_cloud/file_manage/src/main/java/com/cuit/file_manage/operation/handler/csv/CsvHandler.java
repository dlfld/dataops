package com.cuit.file_manage.operation.handler.csv;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.file_manage.bo.OperationBo;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.operation.factory.FileFactory;
import com.cuit.file_manage.operation.handler.AbstractFileHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author wcw
 * @Date: 2022/01/2022/1/1/16:34
 * @Version 1.0
 * @Description
 */

@Component
public class  CsvHandler extends AbstractFileHandler implements InitializingBean {

    /**
     * 获取csv文件中的内容
     *
     * @param operationBo 业务操作对象
     * @return 文件内容
     * @throws IOException 文件不存在
     */
    @Override
    public List<Object> getFileContent(OperationBo operationBo) throws IOException {
        String path = operationBo.getFilePath();
        if (!new File(path).exists()) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        return CsvUtils.getCsvContent(path);
    }

    /**
     * 修改文件单元格内容
     *
     * @param operationBo 业务操作对象
     * @throws IOException
     */
    @Override
    public boolean modifyCell(OperationBo operationBo) throws IOException {
        String path = operationBo.getFilePath();
        Integer row = Math.toIntExact(operationBo.getRowNum());
        Integer col = Math.toIntExact(operationBo.getColNum());
        String value = operationBo.getNewValue().get(0);
        if (!new File(path).exists()) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.modifyCell(path, row, col, value);
        return true;
    }

    /**
     * 删除文件中的row行
     *
     * @param operationBo 业务操作对象
     * @throws IOException
     */

    @Override
    public boolean deleteFileLine(OperationBo operationBo) throws IOException {
        String path = operationBo.getFilePath();
        Integer row = Math.toIntExact(operationBo.getRowNum());
        if (!new File(path).exists()) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.deleteFileLine(path, row);
        return true;
    }

    /**
     * 删除文件指定行
     *
     * @param operationBo 业务操作对象
     * @throws IOException
     */
    @Override
    public boolean deleteFileColumn(OperationBo operationBo) throws IOException {
        String path = operationBo.getFilePath();
        Integer column = Math.toIntExact(operationBo.getColNum());
        if (!new File(path).exists()) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.deleteFileColumn(path, column);
        return true;
    }

    @Override
    public boolean modifyFileColumn(OperationBo operationBo) throws IOException {
        String path = operationBo.getFilePath();
        Integer column = Math.toIntExact(operationBo.getColNum());
        String value = operationBo.getNewValue().get(0);
        if (!new File(path).exists()) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.modifyFileColumn(path, column, value);
        return true;
    }

    /**
     * 注册到工厂中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("注册到工厂中");
        FileFactory.register("csv", this);
    }


}
