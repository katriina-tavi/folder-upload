package com.example.folder.upload.entity;

import lombok.Data;

@Data
public class TreeNodeDTO {
    /**
     * 文件名
     */
    private String name;
    /**
     * 最后一级文件夹名称
     */
    private String folderName;
    /**
     * 全路径文件夹
     */
    private String folderFullName;
}
