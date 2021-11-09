package com.cuit.file_manage.controller;

import com.cuit.api.file_manage.FileInfoApi;
import com.cuit.common.model.response.ResponseData;
import com.cuit.file_manage.service.intf.FileInfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:18 上午
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/file_info")
public class FileInfoController implements FileInfoApi {

    @Resource
    FileInfoService fileInfoService;

    /**
     * 获取文件树信息
     *
     * @return 返回文件树信息
     */
    @Override
    @GetMapping("/tree")
    public ResponseData getFileTreeInfo() {
        return fileInfoService.getFileTreeInfo();
    }
}
