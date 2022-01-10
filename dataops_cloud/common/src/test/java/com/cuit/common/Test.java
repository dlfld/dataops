package com.cuit.common;

import com.cuit.common.model.base.file_manage.DataFile;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/12/28 2:05 PM
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args) {
        String s = "xxx.csv";
        int lastIndexOf = s.lastIndexOf(".");
        String substring = s.substring(0, lastIndexOf);
        System.out.println(substring);
    }
}
