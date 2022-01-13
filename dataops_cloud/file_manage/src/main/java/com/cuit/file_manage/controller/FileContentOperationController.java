package com.cuit.file_manage.controller;

import com.cuit.api.file_manage.FileContentOperationApi;
import com.cuit.common.model.base.file_manage.Operation;
import com.cuit.common.model.base.file_manage.vo.OperationVo;
import com.cuit.common.model.base.file_manage.vo.StartOperationVo;
import com.cuit.common.model.response.ResponseData;
import com.cuit.file_manage.service.intf.FileContentOperationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @Author dailinfeng
 * @Description 对文件内容进行修改的controller
 * @Date 2021/12/28 1:15 PM
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/operation")
public class FileContentOperationController implements FileContentOperationApi {
    @Value(value = "${fileSystem.filePath}")
    String filePathPrefix;
    @Resource
    FileContentOperationService fileContentOperationService;

    /**
     * 对文件内容进行操作，
     *
     * @param operationVo 操作描述
     * @return 添加操作队列结果
     */
    @PostMapping("/handle")
    @Override
    public ResponseData contentUpdate(@RequestBody OperationVo operationVo, HttpServletRequest request) {
        return fileContentOperationService.addOperation(operationVo);
    }

    /**
     * 执行操作的撤回操作，传过来的是当前操作文件的路径
     *
     * @param filePath 当前操作文件的路径
     * @return 返回操作撤回是否成功
     */
    @Override
    @PostMapping("/recall")
    public ResponseData recallOperation(String filePath) {
        return fileContentOperationService.recallOperation(filePath);
    }

    /**
     * 执行操作队列的执行操作
     *
     * @param startOperationVo 提交操作的信息
     * @return 返回操作结果
     */
    @Override
    @PostMapping("/submit")
    public ResponseData startOperation(@Validated @RequestBody StartOperationVo startOperationVo) {
        return fileContentOperationService.startOperation(startOperationVo);
    }


}
