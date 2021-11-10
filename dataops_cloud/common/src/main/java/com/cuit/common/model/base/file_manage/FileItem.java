package com.cuit.common.model.base.file_manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/1 10:23 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileItem implements Serializable {
    /**
     * 文件名
     */
    private String title;
    /**
     * 层数
     */
    private String key;
    /**
     * 额外信息
     */
    private FileExtra extra;
    /**
     * 子节点
     */
    private List<FileItem> children = new ArrayList<>();

    /**
     * 添加一个文件子节点
     *
     * @param item 文件子节点
     */
    public void addItem(FileItem item) {
        children.add(item);
    }
}
