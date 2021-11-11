package com.cuit.common.utils;

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
            System.out.println(e.getMessage() + "错误！");
        } finally {
            try {
                objwt.close();//关闭输出流
            } catch (IOException e) {
            }

        }
    }



}
