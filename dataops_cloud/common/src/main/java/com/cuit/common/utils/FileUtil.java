package com.cuit.common.utils;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.file_manage.FileFinalValue;
import org.springframework.beans.factory.annotation.Value;

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
    public static void copyFileByStream(String sourcePath, String targetPath) {
        //源文件路径
        File source = new File(sourcePath);
        //目标文件路径
        File target = new File(targetPath);

        //如果源文件不存在则不能拷贝
        if (!source.exists()) {
            return;
        }
        //如果目标文件目录不存在则创建
        if (!target.getParentFile().exists()) {
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
            while ((temp = inputStream.read(data)) != -1) {
                //输出数组
                outputStream.write(data, 0, temp);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            //拷贝过程中如果出错，则抛出异常
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.FILE_SHARE_ERROR));
        }
    }

    /**
     * 删除指定path的文件
     *
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


    /**
     * 返回文件的分隔符
     * 操作系统不同，文件的分隔符不同
     *
     * @return 文件分隔符
     */
    public static String getPathSeparator() {
        String systemName = System.getProperty("os.name");
        switch (systemName) {
            case "Windows 10":
                return "\\";
            default:
                return "/";
        }
    }

    /**
     * 获取路径下的所有文件
     *
     * @param path 传入的指定路径
     * @return
     */
    public static File[] getFilesFromPath(String path) {
        File file = new File(path);
        // 获取当前文件路径下的所有文件
        File[] files = file.listFiles();
        return files;
    }

    /**
     * 删除文件夹
     *
     * @param path 删除文件夹的路径
     * @return
     */
    public static boolean deleteLocalDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.FILE_NOT_EXIST));
        } else if (file.isDirectory()) {
            if (file.delete()) {
                System.out.println("删除文件夹成功！");
                return true;
            } else {
                ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.FAILED_TO_DELETE_FILE));
            }
        } else {
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.NOT_A_FILE));
        }
        return true;
    }


    /**
     * 删除（隐藏）全路径下的公共路径
     *
     * @param filePath 文件的全路径
     * @param userName 当前操作用户的用户名
     * @param pathPrefix 当前文件的前置路径
     * @return 删除全路径中用户名前面的一部分（包括用户名）
     */
    public static String deleteFilePathPrefix(String filePath, String userName, String pathPrefix) {
        return filePath.replace(pathPrefix + FileUtil.getPathSeparator() + userName, "");
    }

    /**
     * 将前端传回来的用户文件路径补充完整
     * @param filePath 前端传过来的文件路径
     * @param userName 当前操作用户的用户名
     * @param pathPrefix 当前文件的前置路径
     * @return 添加上前置路径之后的全路径
     */
    public static String addFilePathSuffix(String filePath, String userName, String pathPrefix) {
        return pathPrefix + FileUtil.getPathSeparator() + userName + FileUtil.getPathSeparator() + filePath;
    }

}
