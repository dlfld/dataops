package com.cuit.file_manage.operation.handler.csv;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wcw
 * @Date: 2022/1/1/16:35
 * @Version 1.0
 * @Description
 */
public class CsvUtils {

    /**
     *  查询数据
     * @param path 文件路径
     * @return  整个文件内容组成的集合
     * @throws IOException
     */
    public static List<Object> getCsvContent(String path) throws IOException {
        // 文件读入字符流
        BufferedReader br = new BufferedReader(new FileReader(path));
        // 返回内容
        List<Object> list = new ArrayList<Object>();
        String line = null;
        while ((line = br.readLine())!=null){
            String[] split = line.split(",");
            list.add(split);
        }
        // 关闭字符流
        IOUtils.closeQuietly(br);
        return list;
    }
    /**
     * 修改单元格
     * @param path 文件路径
     * @param row  单元格所在行
     * @param col  单元格所在列
     * @param value 单元格修改的值
     * @throws IOException
     */
    public static void modifyCell(String path, Integer row, Integer col, String value) throws IOException {
        // 字符流
        BufferedReader br =  new BufferedReader(new FileReader(path));
        // 内存流, 作为临时流
        CharArrayWriter tempStream = new CharArrayWriter();
        FileWriter out = null;
        // 初始化位置
        int index = 0;
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                if (index==row){
                    String[] split = line.split(",");
                    split[col] = value;
                    //去除符号空格
                    line = Arrays.toString(split).replaceAll("[\\[\\] +]","");
                }
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
                index++;
            }
            out = new FileWriter(path);
            // 将内存中的流写入文件
            tempStream.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(tempStream);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(br);
        }
    }

    /**
     * 删除Csv文件的row行
     * @param path 文件路径
     * @param row 行
     */
    public static void deleteFileLine(String path, Integer row) throws FileNotFoundException {
        // 字符流
        BufferedReader br =  new BufferedReader(new FileReader(path));
        // 内存流, 作为临时流
        CharArrayWriter tempStream = new CharArrayWriter();
        FileWriter out = null;
        // 初始化位置
        int index = 0;
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                if (index==row){
                    line = "";
                    // 将该行写入内存
                    tempStream.write(line);
                }else {
                    // 将该行写入内存
                    tempStream.write(line);
                    // 添加换行符
                    tempStream.append(System.getProperty("line.separator"));
                }
                index++;
            }
            out = new FileWriter(path);
            // 将内存中的流写入文件
            tempStream.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(tempStream);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(br);
        }
    }


    /**
     *  删除文件所在列
     * @param path 文件路径
     * @param column 列
     * @throws FileNotFoundException 文件不存在
     */
    public static void deleteFileColumn(String path, Integer column) throws FileNotFoundException {
        // 字符流
        BufferedReader br =  new BufferedReader(new FileReader(path));
        // 内存流, 作为临时流
        CharArrayWriter tempStream = new CharArrayWriter();
        FileWriter out = null;
        // 初始化位置
        int index = 0;
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                StringBuilder builder = new StringBuilder();
                //逗号出现的次数
                int time = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i)==','){
                        time++;
                    }
                    if (time!=column){
                        builder.append(line.charAt(i));
                    }
                }
                // 将该行写入内存
                tempStream.write(String.valueOf(builder));
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
                index++;
            }
            out = new FileWriter(path);
            // 将内存中的流写入文件
            tempStream.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(tempStream);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(br);
        }
    }

    /**
     * 修改文件某列信息
     * @param path 文件路径
     * @param column 文件需要修改的列
     * @param value 修改后的值
     */
    public static void modifyFileColumn(String path, Integer column, String value) throws FileNotFoundException {
        // 字符流
        BufferedReader br =  new BufferedReader(new FileReader(path));
        // 内存流, 作为临时流
        CharArrayWriter tempStream = new CharArrayWriter();
        FileWriter out = null;
        // 初始化位置
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                split[column] = value;
                //去除符号空格
                line = Arrays.toString(split).replaceAll("[\\[\\] +]","");
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            }
            out = new FileWriter(path);
            // 将内存中的流写入文件
            tempStream.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(tempStream);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(br);
        }
    }


}
