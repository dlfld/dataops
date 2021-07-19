package com.cuit.dataops.controller;

import com.cuit.dataops.api.FileHandleApi;
import com.cuit.dataops.pojo.response.ResponseData;
import com.cuit.dataops.service.intf.FileHandleService;
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
