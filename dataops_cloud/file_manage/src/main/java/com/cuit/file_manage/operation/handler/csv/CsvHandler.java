package com.cuit.file_manage.operation.handler.csv;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.file_manage.bo.OperationBo;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.operation.factory.FileFactory;
import com.cuit.file_manage.operation.handler.AbstractFileHandler;
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
public class CsvHandler extends AbstractFileHandler {

    /**
     * 获取csv文件中的内容
     * @param operationBo 业务操作对象
     * @return 文件内容
     * @throws IOException 文件不存在
     */
    @Override
    public List<Object> getFileContent(OperationBo operationBo) throws IOException {
        String path = operationBo.getFilePath();
        if (!new File(path).exists()){
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        return CsvUtils.getCsvContent(path);
    }

    /**
     *  修改文件中的单元格
     * @param path 文件路径
     * @param row 修改元素所在行
     * @param col 修改元素所在列
     * @param value 修改后的值
     * @throws IOException 文件不存在
     */
    @Override
    public void modifyCell(String path, Integer row, Integer col, String value) throws IOException {
        if (!new File(path).exists()){
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.modifyCell(path,row,col,value);
    }

    /**
     * 删除文件中的一行
     * @param path 文件路径
     * @param row  文件所在行
     * @throws IOException
     */

    @Override
    public void deleteFileLine(String path, Integer row) throws IOException {
        if (!new File(path).exists()){
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.deleteFileLine(path,row);
    }

    /**
     * 删除文件指定行
     * @param path 文件路径
     * @param column 文件所在行
     * @throws IOException
     */
    @Override
    public void  deleteFileColumn(String path, Integer column)throws IOException{
        if (!new File(path).exists()){
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.deleteFileColumn(path,column);
    }

    @Override
    public void modifyFileColumn(String path, Integer column, String value)throws IOException{
        if (!new File(path).exists()){
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        CsvUtils.modifyFileColumn(path,column,value);
    }
    /**
     *  注册到工厂中
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        FileFactory.register("csv",this);
    }


}
