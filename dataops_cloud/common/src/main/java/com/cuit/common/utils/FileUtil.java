package com.cuit.common.utils;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;

import java.io.*;

/**
 * description
 *
 * @author wcw
 * @date 2021/11/15
 */
public class FileUtil {

    /**
     * 通过字节流实现文件的拷贝
     * @param sourcePath 源文件路径
     * @param targetPath 目标文件路径
     */
    public static void copyFileByStream(String sourcePath,String targetPath){
        //源文件路径
        File source = new File(sourcePath);
        //目标文件路径
        File target = new File(targetPath);

        //如果源文件不存在则不能拷贝
        if(!source.exists()){
            return;
        }
        //如果目标文件目录不存在则创建
        if(!target.getParentFile().exists()){
            target.getParentFile().mkdirs();
        }

        try {
            //实现文件的拷贝
            InputStream inputStream = new FileInputStream(source);
            OutputStream outputStream = new FileOutputStream(target);
            int temp = 0;
            //每次读取1024个字节
            byte[] data = new byte[1024];
            //将每次读取的数据保存到字节数组里面，并且返回读取的个数
            while ((temp = inputStream.read(data)) != -1){
                //输出数组
                outputStream.write(data,0,temp);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            //拷贝过程中如果出错，则抛出异常
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.FILE_SHARE_ERROR));
        }
    }


    /**
     *  删除指定path的文件
      * @param path 删除文件的path
     * @return
     */
    public static boolean deleteLocalFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.FILE_NOT_EXIST));
        } else if (file.isFile()) {
            if (file.delete()) {
                System.out.println("删除文件成功！");
                return true;
            } else {
                ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.FAILED_TO_DELETE_FILE));
            }
        } else {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.NOT_A_FILE));
        }
        return true;
    }
}
