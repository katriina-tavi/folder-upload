package com.example.folder.upload.service;

import com.example.folder.upload.entity.TreeNodeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    /**
     * 文件夹上传
     * @param files
     */
    List<TreeNodeDTO> folderUpload(MultipartFile[] files);
}
