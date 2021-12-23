package com.cuit.file_manage.service.impl;

import com.cuit.common.model.base.file_manage.FileItem;
import com.cuit.common.model.response.ResponseData;
import com.cuit.file_manage.service.intf.ProjectContentService;
import com.cuit.file_manage.utils.ReadDirectory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 8:08 PM
 * @Version 1.0
 */
public class ProjectContentServiceImpl implements ProjectContentService {

    /**
     * 用户的默认根目录
     */
    @Value(value = "${fileSystem.filePath}")
    String userPath;

    /**
     * 获取项目的内容
     *      去指定用户的指定项目文件夹下遍历所有的文件，并生成文件树并返回给前端
     * @param projectName 项目名
     * @param userName    用户名
     * @return 项目下面的目录
     */
    @Override
    public ResponseData getProjectContent(String projectName, String userName) {
        ReadDirectory rd = new ReadDirectory(userPath + "/" + userName);
        FileItem fileItem = rd.getFileItem();
    }
}
