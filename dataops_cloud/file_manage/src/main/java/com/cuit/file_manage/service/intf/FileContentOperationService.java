package com.cuit.file_manage.service.intf;

import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.common.model.base.file_manage.vo.OperationVo;
import com.cuit.common.model.base.file_manage.vo.StartOperationVo;
import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 1:37 PM
 * @Version 1.0
 */
@Service
public interface FileContentOperationService {

    /**
     * 前端传过来一个操作，将这个操作存储到操作队列里面去
     * 如果有操作队列就存到操作队列，如果没有的话就创建一个操作队列
     * 操作队列存储在和所操作对象同一级的目录当中。是一个meta文件
     * @param operationVo
     * @return
     */
    ResponseData addOperation(OperationVo operationVo);

    /**
     * 执行操作的撤回操作，传过来的是当前操作文件的路径
     *
     * @param filePath 当前操作文件的路径
     * @return 返回操作撤回是否成功
     */
    ResponseData recallOperation(String filePath);


    /**
     * 开始执行操作队列的方法
     * @param startOperationVo 开始的调度
     * @return 调度成功之后获取返回结果
     */
    ResponseData startOperation(StartOperationVo startOperationVo);
}
