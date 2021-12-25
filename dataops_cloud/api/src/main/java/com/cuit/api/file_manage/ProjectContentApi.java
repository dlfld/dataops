package com.cuit.api.file_manage;

import com.cuit.common.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @param filePath 文件上传到的指定文件夹
     * @param request request 请求对象  获取请求头
     * @return 文件信息
     */
    @ApiOperation("上传文件")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "filePath",value = "文件存储的路径，从登录时候返回的文件树的根节点开始算,提交文件上传到的路径")
    })
    ResponseData uploadFile(MultipartFile file, String filePath, HttpServletRequest request);


    /**
     * 创建文件夹
     * @param filePath 文件夹的路径
     * @return 创建文件信息
     */
    @ApiOperation("创建文件夹")
    ResponseData createFile(String filePath);
}
