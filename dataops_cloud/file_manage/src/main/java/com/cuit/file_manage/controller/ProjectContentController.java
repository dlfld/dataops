package com.cuit.file_manage.controller;

import com.cuit.api.file_manage.ProjectContentApi;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.RequestUtils;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.ProjectContentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 7:34 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectContentController implements ProjectContentApi {

    @Resource
    ProjectContentService projectContentService;

    /**
     * 获取项目的内容
     *
     * @param projectName 项目名
     * @param request
     * @return
     */
    @Override
    @GetMapping("/{projectName}")
    public ResponseData getProjectContent(@PathVariable String projectName, HttpServletRequest request) {
        String userName = RequestUtils.getUserName(request);
        return projectContentService.getProjectContent(projectName, userName);
    }

    /**
     * 上传文件
     * 前端传进来文件和文件存储的路径
     * 根据文件和文件的路径进行存储
     *
     * @return 文件信息
     */
    @Override
    @PostMapping("/upload_file")
    public ResponseData uploadFile(@RequestPart("file") MultipartFile file, String filePath, HttpServletRequest request) {
        String userName = RequestUtils.getUserName(request);
        return projectContentService.uploadFile(file, filePath, userName);
    }

    /**
     * 创建文件夹
     *
     * @param filePath 文件夹的路径
     * @return 创建文件信息
     */
    @Override
    @PostMapping("/create_file")
    public ResponseData createFile(String filePath) {
        return projectContentService.createFile(filePath);
    }
}
