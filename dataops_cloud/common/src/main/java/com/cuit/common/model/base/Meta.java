package com.cuit.common.model.base;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.utils.ResponseDataUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Author dailinfeng
 * @Description 所有元数据文件的父类
 * @Date 2021/12/28 2:03 PM
 * @Version 1.0
 */
@Slf4j
public abstract class Meta {

    /**
     * 读取元数据文件
     *
     * @param filePath 传入的文件路径
     * @return 元文件中存的对象
     */
    public <T> T metaRead(String filePath, Class<T> beanClass) {
        File file = new File(filePath);
        log.info("读取文件中" + filePath);
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
    public void metaWrite(String filePath, Object obj) {
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
}
