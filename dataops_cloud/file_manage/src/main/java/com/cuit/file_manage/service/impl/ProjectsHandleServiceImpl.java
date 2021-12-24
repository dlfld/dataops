package com.cuit.file_manage.service.impl;

import cn.hutool.core.date.DateTime;
import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.file_manage.FileFinalValue;
import com.cuit.common.model.base.file_manage.Project;
import com.cuit.common.model.base.user_manage.User;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.ProjectsHandleService;
import com.cuit.file_manage.utils.ReadDirectory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

/**
 * @Author wcw
 * @Date 2021/11/1 10:29 上午
 * @Version 1.0
 */
@Service
@Slf4j
public class ProjectsHandleServiceImpl implements ProjectsHandleService {


    /**
     * 用户的默认根目录
     */
    @Value(value = "${fileSystem.filePath}")
    String userPath;

    /**
     * 创建项目
     * @param userName 用户名
     * @return
     */
    @Override
    public ResponseData createProject(String projectName, String userName) {

        String userProjectPath = userPath+FileUtil.getPathSeparator()
                +userName+FileUtil.getPathSeparator()
                +FileFinalValue.projectPath+FileUtil.getPathSeparator();
        // 获取当前文件路径下的所有文件
        File[] files = FileUtil.getFilesFromPath(userProjectPath);
        //如果当前文件夹不为空
        if (!Objects.isNull(files)){
            //判断项目名称有无重复
            for (File file : files) {
                //如果当前文件是文件夹并且存在与projectName相同的名称，表示文件同名
                if (file.isDirectory()&&projectName.equals(file.getName())){
                    ResponseDataUtil.buildError(ResultEnums.PROJECT_EXIST);
                }
            }
        }
        //创建project文件夹
        File file = new File(userProjectPath+projectName);
        //创建文件夹
        file.mkdir();
        // 创建项目对象
        Project project = new Project()
                .setProjectName(projectName)
                .setUserName(userName)
                .setCreateDate(DateTime.now());
        String projectPath = userProjectPath+projectName+ FileFinalValue.fileSuffix;
        MetaFileUtil.metaWrite(projectPath, project);
        log.info(MetaFileUtil.metaRead(projectPath, Project.class).toString());

        return ResponseDataUtil.buildSuccess();
    }
}
