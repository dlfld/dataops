package com.cuit.file_manage.utils;

import com.cuit.common.enums.ResultEnums;
import com.cuit.common.exception.ExceptionCast;
import com.cuit.common.utils.ResponseDataUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * @Author wcw
 * @Date: 2022/1/1/21:42
 * @Version 1.0
 * @Description 文件分块与合并
 */
public class FileChunkUtils {
    /**
     *
     * @param source  源文件路径
     * @param targetFolder 存放块文件目录
     * @param chunkSizeList 块文件大小集合,长度为块文件的数目
     */
    public static void chunk(String source, String targetFolder, List<Integer> chunkSizeList) throws IOException {
        // 源文件路径
        File sourceFile = new File(source);
        RandomAccessFile rr = new RandomAccessFile(sourceFile, "r");
        //缓冲区
        for (int i = 0; i < chunkSizeList.size(); i++) {
            // 每一个块的大小
            Integer chunkFileSize = chunkSizeList.get(i);
            byte[] b = new byte[Math.toIntExact(chunkFileSize)];
            //创建分块文件
            File chunkFile = new File(targetFolder+i);
            //
            RandomAccessFile rw = new RandomAccessFile(chunkFile, "rw");
            int len = -1;
            while ((len = rr.read(b))!=-1){
                rw.write(b,0,len);
                if(chunkFile.length()>=chunkFileSize){
                    break;
                }
            }
            rw.close();
        }
        rr.close();
    }

    /**
     *
     * @param sourceFolder 分块文件的文件夹
     * @param target 最终存储合并文件的路径
     */
    public static void mergeFile(String sourceFolder, String target) throws IOException {
        //块文件目录对象
        File chunkFileFolder = new File(sourceFolder);
        //块文件列表
        File[] files = chunkFileFolder.listFiles();
        //将块文件排序，按名称升序
        if (Objects.isNull(files)){
            ExceptionCast.cast(ResponseDataUtil.buildError(ResultEnums.PATH_IS_EMPTY));
        }
        List<File> fileList = Arrays.asList(files);
        fileList.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName())) {
                    // 升序
                    return 1;
                }
                // 降序
                return -1;
            }
        });
        File mergeFile = new File(target);
        //创建写对象
        RandomAccessFile rw = new RandomAccessFile(mergeFile,"rw");
        byte[] b = new byte[1024];
        for (File file : fileList) {
            //创建一个读块文件的对象
            RandomAccessFile rd = new RandomAccessFile(file,"r");
            int len = -1;
            while((len = rd.read(b))!=-1){
                rw.write(b,0,len);
            }
            rd.close();
        }
        rw.close();
    }
}
