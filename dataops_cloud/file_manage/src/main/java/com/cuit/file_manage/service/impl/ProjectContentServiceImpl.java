package com.cuit.file_manage.service.impl;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.file_manage.FileFinalValue;
import com.cuit.common.model.base.file_manage.FileItem;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.ProjectContentService;
import com.cuit.file_manage.utils.ReadDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 8:08 PM
 * @Version 1.0
 */
@Service
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
        //获取文件路径分隔符
        String pathSeparator = FileUtil.getPathSeparator();
        String projectPath = userPath + pathSeparator + userName+pathSeparator+ FileFinalValue.projectPath;
        File file = new File(projectPath);
        //如果文件不存在的话就表示当前项目不存在
        if (!file.exists()){
            return ResponseDataUtil.buildError(ResultEnums.PROJECT_NOT_FOUND);
        }
        //获取项目目录树
        ReadDirectory rd = new ReadDirectory(projectPath);
        FileItem fileItem = rd.getFileItem();
        return ResponseDataUtil.buildSuccess(fileItem);
    }
}
