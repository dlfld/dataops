package com.cuit.scheduling.service.impl;

import com.cuit.scheduling.service.intf.ResultHandleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/7/29 8:50 下午
 * @Version 1.0
 */
@Component
public class ResultHandleServiceImpl implements ResultHandleService {

    @Override
    public void downloadResultFile(String fileName, HttpServletResponse httpServletResponse)  {
        // 如果文件名不为空，则进行下载
        if (StringUtils.isNotEmpty(fileName)) {
            //设置文件路径
            String realPath = "/Users/dailinfeng/Desktop/dataops/result/"+fileName;
            File file = new File(realPath);
            System.out.println(file);
            System.out.println(file.exists());
            // 如果文件名存在，则进行下载
            if (file.exists()) {
                System.out.println("进来了");
                // 配置文件下载
                httpServletResponse.setHeader("content-type", "application/octet-stream");
                httpServletResponse.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                try{
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                }catch (Exception e){
                    e.printStackTrace();
                }
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = httpServletResponse.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the song failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
}
