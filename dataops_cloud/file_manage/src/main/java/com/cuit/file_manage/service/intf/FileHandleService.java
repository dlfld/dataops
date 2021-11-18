package com.cuit.file_manage.service.intf;

import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author dailinfeng
 * @Description 处理文件相关信息
 * @Date 2021/11/17 3:46 下午
 * @Version 1.0
 */
@Service
public interface FileHandleService {
    /**
     * 上传文件 指定文件夹
     * @param file 文件对象
     * @param filePath 文件夹
     * @param userName 用户名，也就是上传文件的人
     * @return
     */
    ResponseData uploadFile(MultipartFile file, String filePath,String userName);
}
