package com.cuit.file_manage.service.impl;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.model.base.file_manage.DataFile;
import com.cuit.common.model.base.file_manage.FileItem;
import com.cuit.common.model.base.file_manage.vo.FileShareInfoVo;
import com.cuit.common.model.response.ResponseData;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.MetaFileUtil;
import com.cuit.common.utils.ResponseDataUtil;
import com.cuit.file_manage.service.intf.FileInfoService;
import com.cuit.file_manage.utils.ReadDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:29 上午
 * @Version 1.0
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Value(value = "${fileSystem.filePath}")
    String filePath;

    /**
     * 用户的默认根目录
     */
    @Value(value = "${fileSystem.userPath}")
    String userPath;

    /**
     * 文件后缀
     */
    protected final String fileSuffix = ".meta";


    /**
     * 获取指定文件夹下的文件树
     *
     * @return 文件树
     */
    @Override
    public ResponseData getFileTreeInfo() {
        ReadDirectory rd = new ReadDirectory(filePath);
        FileItem fileItem = rd.getFileItem();
        return ResponseDataUtil.buildSuccess(fileItem);
    }
    /**
     * 用户分享文件
     * @param fileShareInfoVo
     * @return
     */
    @Override
    public ResponseData shareFile(FileShareInfoVo fileShareInfoVo) {
        //判断对象中的参数是否为空 todo

        // 被分享者的默认地址
        String path = userPath+"/"+fileShareInfoVo.getShareName()+"/share/";
        //获取分享者分享当前文件的地址 FileFullPath分享者分享文件的全路径
        File file = new File(fileShareInfoVo.getFileFullPath());
        //获取分享者分享当前文件的元文件
        String mateFilePath = MetaFileUtil.getMateFilePath(file.getPath());
        File mateFile = new File(mateFilePath);
        //判断当前分享者分享的文件和元文件是否存在,如果当前文件不存在，表示文件缺失
        if (!file.exists() && !mateFile.exists()) {
            ResponseDataUtil.buildError(ResultEnums.FILE_LOST);
        }
        //分享文件
        FileUtil.copyFileByStream(file.getPath(),path.concat(file.getName()));
        //被分享者的当前文件的mate路径
        String matePath = MetaFileUtil.getMateFilePath(path.concat(file.getName()));
        //分享元文件
        FileUtil.copyFileByStream(mateFilePath, matePath);
        //获取当前元文件对象
        DataFile df = MetaFileUtil.metaRead(matePath, DataFile.class);
        //如果当前元文件对象不等于空
        if (!Objects.isNull(df)){
            df.setDownloadable(fileShareInfoVo.getDownloadable())
                    .setStart(fileShareInfoVo.getStart())
                    .setEnd(fileShareInfoVo.getEnd())
                    .setFileShareDeadline(fileShareInfoVo.getFileShareDeadLine())
                    .setFileShareAttributeList(fileShareInfoVo.getFileShareAttributeList());
        }
        //写入mate文件
        MetaFileUtil.metaWrite(matePath, DataFile.class);
        //成功
        return ResponseDataUtil.buildSuccess();
    }

}
