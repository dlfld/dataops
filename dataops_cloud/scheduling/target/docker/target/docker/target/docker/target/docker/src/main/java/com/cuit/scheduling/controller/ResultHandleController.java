package com.cuit.scheduling.controller;

import com.cuit.api.scheduling.ResultHandleApi;
import com.cuit.scheduling.service.intf.ResultHandleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/29 8:46 下午
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/result")
public class ResultHandleController implements ResultHandleApi {
    @Resource
    ResultHandleService resultHandleService;

    /**
     * 下载结果
     *
     * @param fileName
     * @param httpServletResponse
     */
    @Override
    @GetMapping("/{fileName}")
    public void downloadResultFile(@PathVariable String fileName, HttpServletResponse httpServletResponse) {
        System.out.println(fileName);
        resultHandleService.downloadResultFile(fileName, httpServletResponse);
    }
}
