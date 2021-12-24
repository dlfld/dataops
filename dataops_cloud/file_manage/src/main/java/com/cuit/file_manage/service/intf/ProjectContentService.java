package com.cuit.file_manage.service.intf;


import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 8:03 PM
 * @Version 1.0
 */
public interface ProjectContentService {

    /**
     * 获取项目的内容
     * @param projectName 项目名
     * @param userName 用户名
     * @return 项目下面的目录
     */
    ResponseData getProjectContent(String projectName, String userName);

    /**
     * 上传文件 指定文件夹
     * @param file 文件对象
     * @param filePath 文件夹
     * @param userName 用户名，也就是上传文件的人
     * @return
     */
    ResponseData uploadFile(MultipartFile file, String filePath, String userName);
}
