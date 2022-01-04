package com.cuit.file_manage.utils;

import com.cuit.common.model.base.file_manage.FileFinalValue;
import com.cuit.common.model.base.file_manage.FileItem;
import com.cuit.common.utils.FileUtil;
import com.cuit.common.utils.MetaFileUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;

/**
 * @author dailinfeng
 */
@Data
@NoArgsConstructor
@Slf4j
public class ReadDirectory {
    /**
     * 文件所在的层数
     */
    private int fileLevel;
    /**
     * 顶层文件对象
     */
    private FileItem topFile;

    /**
     * 文件路径
     */
    private String dirPath;

    /**
     * 生成输出格式
     *
     * @param level 输出的文件名或者目录名所在的层次
     * @return 输出的字符串
     */
    private String getKey(int level, int num) {
        // 输出的前缀
        StringBuilder key = new StringBuilder("0-0");
//        String key = "0-0";
        // 按层次进行缩进
        for (int i = 0; i < level; i++) {
            key.append("-0");
        }
        key.append("-").append(num);
        return key.toString();
    }

    /**
     * 初始化文件对象
     *
     * @param dirPath 文件路径
     */
    public ReadDirectory(String dirPath) {
//        dirPath = FileUtil.deleteFilePathPrefix(dirPath,);
        topFile = new FileItem()
                .setTitle(dirPath)
                .setKey("0-0")
                .setExtra(null);
        this.dirPath = dirPath;
    }

    /**
     * 输出给定目录下的文件，包括子目录中的文件
     *
     * @param dirPath 给定的目录
     */
    private void readFile(String dirPath, FileItem topFile) {
        // 建立当前目录中文件的File对象
        log.info(dirPath);
        File file = new File(dirPath);

        // 取得代表目录中所有文件的File对象数组
        File[] list = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return false;
            }
        });

        // 遍历file数组
        for (int i = 0; i < list.length; i++) {
            //如果当前文件对象是文件夹
            if (list[i].isDirectory()) {
                //当前文件的mate文件路径
                String mateFilePath = MetaFileUtil.getMateDirectoryPath(list[i].getPath());
                //生成当前文件的文件对象
                FileItem fileItem = new FileItem()
                        .setTitle(list[i].getName())
                        .setExtra(MetaFileUtil.getDataSetInformation(mateFilePath))
                        .setKey(getKey(fileLevel, i));
                topFile.addItem(fileItem);
                fileLevel++;
                // 递归子目录 传当前的文件对象进去，因为在这儿递归的是当前文件夹的子文件夹
                readFile(list[i].getPath(), fileItem);
                fileLevel--;
            } else {

                //当前文件对象是一个文件 不是文件夹
                // 判断该文件是否为mate文件,当该文件不为mate文件的时候加入到文件树中 并且判断文件是否有元数据文件，如果有就将元数据内容加入返回内容中，如果没有就不加入Extra
                if (!MetaFileUtil.isMateFile(list[i].getName())) {
                    //获取当前文件对应的meta文件的地址
                    String mateFilePath = null;
                    //如果一个文件没有.的话 就会报错，因此需要看这个文件是否有.没有的话就直接返回文件名就行了
                    if (list[i].getPath().contains(".")) {
                        mateFilePath = list[i].getPath().substring(0, list[i].getPath().lastIndexOf(".")) + FileFinalValue.fileSuffix;
                    } else {
                        mateFilePath = list[i].getName() + FileFinalValue.fileSuffix;
                    }

                    FileItem fileItem = new FileItem()
                            .setTitle(list[i].getName())
                            .setExtra(MetaFileUtil.getDataFileInformation(mateFilePath))
                            .setKey(getKey(fileLevel, i));

                    topFile.addItem(fileItem);
                }
            }
        }
    }

    public FileItem getFileItem() {
        //读文件
        readFile(dirPath, topFile);
        // 返回结果

        return topFile;
    }

}
