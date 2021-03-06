package com.cuit.common.utils;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.model.base.file_manage.DataFile;
import com.cuit.common.model.base.file_manage.Folder;
import com.cuit.common.model.base.file_manage.FileExtra;
import com.cuit.common.model.base.file_manage.FileFinalValue;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author wangchangwu
 * 元数据读写的工具类
 */
@Slf4j
public class MetaFileUtil {


    /**
     * 读取元数据文件
     *
     * @param filePath 传入的文件路径
     * @return 元文件中存的对象
     */
    public static <T> T metaRead(String filePath, Class<T> beanClass) {
        File file = new File(filePath);
        log.info("读取文件中"+filePath);
        //如果文件不存在的话直接返回null
        if (!file.exists()) {
            return null;
        }
        // 文件的读取
        ObjectInputStream objrd = null;
        try {
            objrd = new ObjectInputStream(new FileInputStream(file));
            return beanClass.cast(objrd.readObject());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "错误!");
        } finally {
            try {
                objrd.close();// 关闭输入流
            } catch (IOException e) {
            }

        }
        return null;
    }

    /**
     * 写元数据文件
     *
     * @param filePath 文件路径
     * @param obj      写入对象
     */
    public static void metaWrite(String filePath, Object obj) {
        File file = new File(filePath);
        ObjectOutputStream objwt = null;
        try {
            // 把对象写入到文件中，使用ObjectOutputStream
            objwt = new ObjectOutputStream(
                    new FileOutputStream(file));
            // 把对象写入到文件中
            objwt.writeObject(obj);
        } catch (IOException e) {
            //抛出元文件写入失败的异常
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.META_WRITE_FAIL));
        } finally {
            try {
                objwt.close();//关闭输出流
            } catch (IOException e) {
            }

        }
    }

    /**
     * 判断当前文件是否为mate文件
     *
     * @param fileName 文件名称
     * @return 判断结果
     */
    public static boolean isMateFile(String fileName) {
        return fileName.contains(FileFinalValue.fileSuffix);
    }

    /**
     * 获取当前文件的mate文件中的对象信息
     *
     * @param filePath mate的路径
     * @return 返回一个map，key为数据文件名称，value为数据文件分享到期时间
     */
    public static FileExtra getDataFileInformation(String filePath) {
        //获取该文件的元文件中的对象
        DataFile df = metaRead(filePath, DataFile.class);
        // 判断是否为空
        if (null != df) {
            return new FileExtra()
                    .setFileName(df.getFileName())
                    .setDeadline(df.getFileShareDeadline());
        } else {
            //抛出异常，当文件为空
            return null;
        }
    }

    /**
     * 获取当前文件夹的mate文件中的对象信息
     *
     * @param filePath mate的路径
     * @return 返回一个map，key为数据集名称，value为数据集分享到期时间
     */
    public static FileExtra getDataSetInformation(String filePath) {
        //获取该文件的元文件中的对象
        Folder df = metaRead(filePath, Folder.class);
        // 判断是否为空
        if (null != df) {
            return new FileExtra()
                    .setFileName(df.getDataSetName())
                    .setDeadline(df.getDataSetShareDeadline());
        } else {
            //抛出异常，当文件为空
            log.info("元文件信息为空:"+filePath);
//            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.NULL_META_MESSAGE));
            return null;
        }
    }

    /**
     * 根据文件目录获取文件的元文件路径
     * @param filePath 当前文件的绝对路径
     * @return  当前文件对应的元文件的绝对路径
     */
    public static String getMateFilePath(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            //如果文件不存在就报错文件找不到
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.FILE_NOT_FOUND));
        }
        //如果是没有后缀的文件的话就直接加.meta返回
        if(!filePath.contains(".")){
            return filePath+FileFinalValue.fileSuffix;
        }
        int lastIndexOf = filePath.lastIndexOf(".");
        return filePath.substring(0,lastIndexOf+1)+FileFinalValue.fileSuffix;
    }

    public static String getMateDirectoryPath(String path){
        return path.concat(FileFinalValue.fileSuffix);
    }



}
