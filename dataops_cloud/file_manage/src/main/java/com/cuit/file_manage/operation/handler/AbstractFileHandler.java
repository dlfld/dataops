package com.cuit.file_manage.operation.handler;

import com.cuit.common.model.base.file_manage.bo.OperationBo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 文件处理抽象类
 * @Author wcw
 * @Date: 2022/01/2022/1/12/16:27
 * @Version 1.0
 * @Description
 */
@Component
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
     * @param  operationBo 业务操作对象
     * @throws IOException
     */
    public boolean modifyCell(OperationBo operationBo) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除文件中的row行
     * @param operationBo 业务操作对象
     * @throws IOException
     */
    public boolean deleteFileLine(OperationBo operationBo) throws IOException{
        throw new UnsupportedOperationException();
    }

    /**
     * 删除文件
     * @param operationBo 业务操作对象
     * @throws IOException 文件异常
     */
    public boolean  deleteFileColumn(OperationBo operationBo)throws IOException{
        throw new UnsupportedOperationException();
    }


    public boolean modifyFileColumn(OperationBo operationBo) throws IOException {
        throw new UnsupportedOperationException();
    }






}
