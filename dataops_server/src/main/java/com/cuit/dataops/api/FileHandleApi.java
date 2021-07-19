package com.cuit.dataops.api;

import com.cuit.dataops.pojo.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/19 5:02 下午
 * @Version 1.0
 */
@Api(tags = "文件处理相关接口")
public interface FileHandleApi {
    @ApiOperation("文件上传")
    ResponseData fileUpload(MultipartFile file);
}
