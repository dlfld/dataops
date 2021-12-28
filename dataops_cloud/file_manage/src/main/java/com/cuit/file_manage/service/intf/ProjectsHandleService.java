package com.cuit.file_manage.service.intf;

import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @author wcw
 */
@Service
public interface ProjectsHandleService {

    /**
     * 从token中获取的用户名
     * @param userName 用户名
     * @return
     */
    ResponseData createProject(String projectName, String userName);

    /**
     * 查询项目
     * @param projectType 项目类型
     * @param userName 查询者
     * @return
     */
    ResponseData searchProjects(String projectType, String userName);

    /**
     * 删除项目
     * @param projectName 项目名称
     * @param userName 删除者
     * @return
     */
    ResponseData deleteProject(String projectName, String userName);

}
