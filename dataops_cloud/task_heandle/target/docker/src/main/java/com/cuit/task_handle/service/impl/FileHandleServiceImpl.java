package com.cuit.task_handle.service.impl;

import cn.hutool.core.io.FileUtil;
import com.cuit.common.pojo.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.task_handle.service.intf.FileHandleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/19 5:07 下午
 * @Version 1.0
 */
@Service
public class FileHandleServiceImpl implements FileHandleService {
    /**
     * 存上传的数据的位置
     */
    @Value(value = "${data.dataPath}")
    String dataPath;

    /**
     * 获取数据文件夹下保存的所有文件
     *
     * @param file
     * @return
     */
    @Override
    public ResponseData uploadFile(MultipartFile file) {
        boolean exist = FileUtil.exist(dataPath);
        //如果文件夹不存在就创建文件夹
        if (!exist) {
            FileUtil.mkdir(dataPath);
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        String filePath = dataPath + "/";
        try {
            file.transferTo(new File(filePath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseDataUtil.buildSuccess(newName);
    }
}
