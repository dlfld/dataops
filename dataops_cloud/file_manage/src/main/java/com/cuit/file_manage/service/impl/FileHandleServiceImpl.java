package com.cuit.file_manage.service.impl;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.FileHandleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/17 3:47 下午
 * @Version 1.0
 */
@Component
public class FileHandleServiceImpl implements FileHandleService {

    /**
     * 文件前缀
     */
    @Value(value = "${fileSystem.filePath}")
    private String prePath;

    /**
     * 上传文件 指定文件夹
     * 上传文件还要包括自动生成部分的元数据文件
     *
     * @param file     文件对象
     * @param filePath 文件夹
     * @return
     */
    @Override
    public ResponseData uploadFile(MultipartFile file, String filePath) {
        //创建文件储存对象
        File savePath = new File(filePath);
        boolean exist = savePath.exists();
        //如果文件夹不存在抛异常
        if (!exist) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        //文件夹存在的情况下
        String oldName = file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath, oldName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseDataUtil.buildSuccess(oldName);
    }

}
