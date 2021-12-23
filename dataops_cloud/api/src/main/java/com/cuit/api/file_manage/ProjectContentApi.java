package com.cuit.api.file_manage;

import com.cuit.common.model.response.ResponseData;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/23 7:17 PM
 * @Version 1.0
 */
@Api(tags = "项目信息处理")
public interface ProjectContentApi {

    /**
     * 获取项目的内容
     * @param projectName 项目名
     * @return
     */
    ResponseData getProjectContent(String projectName, HttpServletRequest request);
}
