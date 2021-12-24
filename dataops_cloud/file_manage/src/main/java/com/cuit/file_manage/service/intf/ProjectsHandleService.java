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
}
