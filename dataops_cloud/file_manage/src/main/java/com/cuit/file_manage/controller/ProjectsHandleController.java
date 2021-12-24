package com.cuit.file_manage.controller;

import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.RequestUtils;
import com.cuit.file_manage.service.intf.ProjectsHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wcw
 */
@RestController
@CrossOrigin
@RequestMapping("/projects_handle")
@Slf4j
public class ProjectsHandleController {

    @Resource
    private ProjectsHandleService projectsHandleService;

    /**
     * 创建项目
     * @param projectName 项目名称
     * @param request
     * @return
     */
    @RequestMapping("/create_project")
    public ResponseData createProject(String projectName, HttpServletRequest request) {
        //获取请求头中的用户名
        String userName = RequestUtils.getUserName(request);
        return projectsHandleService.createProject(projectName, userName);
    }


}
