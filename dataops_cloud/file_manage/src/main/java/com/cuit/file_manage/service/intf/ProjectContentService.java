package com.cuit.file_manage.service.intf;


import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Component;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 8:03 PM
 * @Version 1.0
 */
@Component
public interface ProjectContentService {

    /**
     * 获取项目的内容
     * @param projectName 项目名
     * @param userName 用户名
     * @return 项目下面的目录
     */
    ResponseData getProjectContent(String projectName, String userName);
}
