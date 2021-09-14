package com.cuit.scheduling.service.intf;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/29 8:48 下午
 * @Version 1.0
 */
@Service
public interface ResultHandleService {

    /**
     *
     * @param fileName
     * @param httpServletResponse
     */
    void downloadResultFile(String fileName, HttpServletResponse httpServletResponse);
}
