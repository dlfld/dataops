package com.cuit.file_manage.service.impl;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.file_manage.DataFile;
import com.cuit.common.model.base.file_manage.FileFinalValue;
import com.cuit.common.model.base.file_manage.FileItem;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.ProjectContentService;
import com.cuit.file_manage.utils.ReadDirectory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 8:08 PM
 * @Version 1.0
 */
@Service
@Slf4j
public class ProjectContentServiceImpl implements ProjectContentService {

    /**
     * 用户的默认根目录
     */
    @Value(value = "${fileSystem.filePath}")
    String userPath;

    /**
     * 获取项目的内容
     * 去指定用户的指定项目文件夹下遍历所有的文件，并生成文件树并返回给前端
     *
     * @param projectName 项目名
     * @param userName    用户名
     * @return 项目下面的目录
     */
    @Override
    public ResponseData getProjectContent(String projectName, String userName) {
        //获取文件路径分隔符
        String pathSeparator = FileUtil.getPathSeparator();
        String projectPath = userPath + pathSeparator + userName + pathSeparator + FileFinalValue.projectPath;
        File file = new File(projectPath);
        //如果文件不存在的话就表示当前项目不存在
        if (!file.exists()) {
            return ResponseDataUtil.buildError(ResultEnums.PROJECT_NOT_FOUND);
        }
        //获取项目目录树
        ReadDirectory rd = new ReadDirectory(projectPath);
        FileItem fileItem = rd.getFileItem();
        return ResponseDataUtil.buildSuccess(fileItem);
    }

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
