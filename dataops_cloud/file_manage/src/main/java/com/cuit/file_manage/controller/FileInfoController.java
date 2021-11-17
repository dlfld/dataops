package com.cuit.file_manage.controller;

import com.cuit.api.file_manage.FileInfoApi;
import com.cuit.common.model.base.file_manage.vo.FileShareInfoVo;
import com.cuit.common.model.response.ResponseData;
import com.cuit.file_manage.service.intf.FileInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:18 上午
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/file_info")
public class FileInfoController implements FileInfoApi {

    @Resource
    FileInfoService fileInfoService;

    /**
     * 获取文件树信息
     *
     * @return 返回文件树信息
     */
    @Override
    @GetMapping("/tree")
    public ResponseData getFileTreeInfo(HttpServletRequest request) {
        String userName = request.getHeader("userName");
        return fileInfoService.getFileTreeInfo(userName);
    }


    /**
     * 分享文件
     *
     * @param param 接收文件分享信息实体类的参数
     * @return 成功或者失败
     */
    @Override
    @PostMapping("/shareFile")
    public ResponseData shareFile(FileShareInfoVo param) {
        return fileInfoService.shareFile(param);
    }


}
