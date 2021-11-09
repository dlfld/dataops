package com.cuit.file_manage.service.intf;

import com.cuit.common.model.response.ResponseData;
import org.springframework.stereotype.Service;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:29 上午
 * @Version 1.0
 */
@Service
public interface FileInfoService {

    /**
     *获取指定文件夹下的文件树
     * @return 文件树
     */
    ResponseData getFileTreeInfo();
}
