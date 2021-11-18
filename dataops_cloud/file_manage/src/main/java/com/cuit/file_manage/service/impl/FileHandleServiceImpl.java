package com.cuit.file_manage.service.impl;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.file_manage.DataFile;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.FileHandleService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
     * @param userName 用户名 也就是上传文件的人
     * @return
     */
    @Override
    public ResponseData uploadFile(MultipartFile file, String filePath, String userName) {
        String fileSavaPath = prePath + "/" + filePath;
        log.info(fileSavaPath);
        //创建文件储存对象
        File saveFile = new File(prePath + "/" + filePath);
        boolean exist = saveFile.exists();
        //如果文件夹不存在抛异常
        if (!exist) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST));
        }
        //文件夹存在的情况下
        String oldName = file.getOriginalFilename();
        try {
            file.transferTo(new File(fileSavaPath, oldName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //文件存储完成之后生成文件的元数据文件
        DataFile dataFile = new DataFile()
                .setFileName(oldName)
                .setFileUploader(userName)
                .setDownloadable(false);
        //写入元文件
        String fileSuffix = oldName.substring(oldName.lastIndexOf("."));
        log.info(fileSuffix);
        MetaFileUtil.metaWrite(fileSavaPath + "/" + oldName, dataFile);
        return ResponseDataUtil.buildSuccess();
    }

}
