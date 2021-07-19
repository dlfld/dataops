package com.cuit.dataops.controller;

import com.cuit.dataops.api.FileHandleApi;
import com.cuit.dataops.pojo.response.ResponseData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public ResponseData fileUpload(MultipartFile file) {
        return null;
    }
}
