package com.cuit.file_manage.service.impl;

import com.cuit.common.model.base.file_manage.FileItem;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.FileInfoService;
import com.cuit.file_manage.utils.ReadDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:29 上午
 * @Version 1.0
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Value(value = "${fileSystem.filePath}")
    String filePath;

    /**
     * 获取指定文件夹下的文件树
     *
     * @return 文件树
     */
    @Override
    public ResponseData getFileTreeInfo() {
        ReadDirectory rd = new ReadDirectory(filePath);
        FileItem fileItem = rd.getFileItem();
        return ResponseDataUtil.buildSuccess(fileItem);
    }
}
