package com.cuit.dataops.service.intf;

import com.cuit.dataops.pojo.response.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/19 5:07 下午
 * @Version 1.0
 */
@Service
public interface FileHandleService {
    /**
     * 获取数据文件夹下保存的所有文件
     *
     * @return
     */
//    ResponseData getFiles();

    ResponseData uploadFile(MultipartFile file);


}
