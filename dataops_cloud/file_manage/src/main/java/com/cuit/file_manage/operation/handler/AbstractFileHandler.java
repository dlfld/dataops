package com.cuit.file_manage.operation.handler;

import com.cuit.common.model.base.file_manage.bo.OperationBo;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.List;

/**
 * 文件处理抽象类
 * @Author wcw
 * @Date: 2022/01/2022/1/12/16:27
 * @Version 1.0
 * @Description
 */
public abstract class AbstractFileHandler implements InitializingBean {

    /**
     * 查询文件中的内容
     * @param operationBo 业务操作对象
     * @return
     * @throws IOException
     */
    public List<Object> getFileContent(OperationBo operationBo) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * 修改文件单元格内容
     * @param path 文件路径
     * @param row 修改元素所在行
     * @param col 修改元素所在列
     * @param value 修改后的值
     * @throws IOException
     */
    public void modifyCell(String path, Integer row, Integer col, String value) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除文件中的row行
     * @param path 文件路径
     * @param row 文件所在行
     * @throws IOException
     */
    public void deleteFileLine(String path, Integer row) throws IOException{
        throw new UnsupportedOperationException();
    }

    /**
     * 删除文件
     * @param path 文件路径
     * @param column 文件所在行
     * @throws IOException 文件异常
     */
    public void  deleteFileColumn(String path, Integer column)throws IOException{
        throw new UnsupportedOperationException();
    }

    public void modifyFileColumn(String path, Integer column,String value) throws IOException {
        throw new UnsupportedOperationException();
    }






}
