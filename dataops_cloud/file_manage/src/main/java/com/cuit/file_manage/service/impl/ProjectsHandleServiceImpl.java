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
import java.util.ArrayList;
import java.util.List;
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

    /**
     * 查询项目
     * @param projectType 项目类型 包含 projects于share
     * @param userName 用户名称
     * @return ResponseData
     */
    @Override
    public ResponseData searchProjects(String projectType, String userName) {
        //当前用户文件路径
        String userProjectPath = userPath + userName+FileUtil.getPathSeparator();
        //定义用户查询项目的路径
        String path = null;
        //判断projectType类型
        if (FileFinalValue.projectPath.equals(projectType)){
            //当前项目文件夹路径
            path = userProjectPath+FileFinalValue.projectPath+FileUtil.getPathSeparator();
        }else if (FileFinalValue.sharePath.equals(projectType)){
            path = userProjectPath+FileFinalValue.sharePath+FileUtil.getPathSeparator();
        }
        //如果当前path为空证明projectType不合法
        if (Objects.isNull(path)){
            return ResponseDataUtil.buildError(ResultEnums.PATH_NOT_EXIST);
        }
        //创建返回给前端的project集合
        List<Project> projectList = new ArrayList<>();
        //获取该目录下的所有文件
        File[] files = FileUtil.getFilesFromPath(path);
        for (File file : files) {
            // 如果该文件为meta文件则读取其中的信息
            if (file.getName().contains(FileFinalValue.fileSuffix)){
                // 获取该文件的meta信息
                Project project = MetaFileUtil.metaRead(file.getPath(),Project.class);
                // 加入到集合之中
                projectList.add(project);
            }
        }
        return ResponseDataUtil.buildSuccess(projectList);
    }

    /**
     * 删除项目
     * @param projectName 项目名称
     * @param userName 删除者
     * @return ResponseData
     */
    @Override
    public ResponseData deleteProject(String projectName, String userName) {

//        //查询用户分享列表
//        List<ShareProjectMsg> shareDataMsgList = Objects.requireNonNull(MetaFileUtil.metaRead(userPath+userName, User.class)).getShareProjectMsgs();
//
//        //获取当前删除这个项目分享者名称
//        List<String> sharedNames = shareDataMsgList.stream()
//                .filter(e->e.projectName.equals(projectName))
//                .map(ShareProjectMsg::getUserName)
//                .collect(Collectors.toList());
//        //todo 假定在同一台机器上
//
//        //删除该项目
//        for (String sharedName : sharedNames) {
//            String path = userPath+sharedName+FileUtil.getPathSeparator()
//                    +FileFinalValue.sharePath+FileUtil.getPathSeparator()
//                    +sharedName;
//            // 删除当前项目文件夹
//            FileUtil.deleteLocalDirectory(path);
//            // 删除当前项目元文件
//            FileUtil.deleteLocalFile(path.concat(FileFinalValue.fileSuffix));
//        }

        //要删除项目的路径
        String filePath = userPath+userName+FileUtil.getPathSeparator()
                +FileFinalValue.projectPath+FileUtil.getPathSeparator()
                +projectName;
        System.out.println(filePath);
        //删除项目文件夹
        FileUtil.deleteLocalDirectory(filePath);
        //删除项目文件夹对应的meta文件
        FileUtil.deleteLocalFile(filePath.concat(FileFinalValue.fileSuffix));
        return ResponseDataUtil.buildSuccess();
    }
}
