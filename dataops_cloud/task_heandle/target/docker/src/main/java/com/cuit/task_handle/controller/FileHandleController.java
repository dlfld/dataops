package com.cuit.task_handle.controller;

import com.cuit.api.task_handle.FileHandleApi;
import com.cuit.common.pojo.response.ResponseData;
import com.cuit.task_handle.service.intf.FileHandleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/19 5:06 下午
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileHandleController implements FileHandleApi {
    @Resource
    FileHandleService fileHandleService;

    @Override
    @PostMapping("/upload")
    public ResponseData fileUpload(@RequestPart("file") MultipartFile file) {
        return fileHandleService.uploadFile(file);
    }
}
