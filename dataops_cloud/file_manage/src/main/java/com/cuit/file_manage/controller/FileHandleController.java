package com.cuit.file_manage.controller;

import com.cuit.api.file_manage.FilehandleApi;
import com.cuit.common.model.response.ResponseData;
import com.cuit.file_manage.service.intf.FileHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/17 3:42 下午
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/file_handle")
@Slf4j
public class FileHandleController implements FilehandleApi {

    @Resource
    FileHandleService filehandleService;
    /**
     * 上传文件
     * 前端传进来文件和文件存储的路径
     * 根据文件和文件的路径进行存储
     * @return 文件信息
     */
    @Override
    @PostMapping("/upload_file")
    public ResponseData uploadFile(@RequestPart("file") MultipartFile file, String filePath,HttpServletRequest request) {
        String userName = request.getHeader("userName");
        log.info(userName);
        userName = "dlf";
        return filehandleService.uploadFile(file,filePath,userName);
    }

    /**
     * 创建文件夹
     *
     * @param filePath 文件夹的路径
     * @return 创建文件信息
     */
    @Override
    public ResponseData createFile(String filePath) {
        return null;
    }
}
