package com.example.folder.upload.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.folder.upload.entity.TreeNodeDTO;
import com.example.folder.upload.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    /**
     * 文件夹上传
     *
     * @param files
     */
    public List<TreeNodeDTO> folderUpload(MultipartFile[] files) {
        List<TreeNodeDTO> treeNodeDTOList = new ArrayList<>();
        if (files == null || files.length == 0) {
            return new ArrayList<>();
        }
        String firstFolder = null;
        for (MultipartFile file : files) {
            TreeNodeDTO treeNodeDTO = new TreeNodeDTO();
            String filePath = file.getOriginalFilename();
            if (filePath.lastIndexOf(StrUtil.C_SLASH) > 0) {
                String dirPath = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(StrUtil.C_SLASH));
                // 设置文件所在目录的全目录路径
                treeNodeDTO.setFolderFullName(dirPath);
                String[] split = dirPath.split(StrUtil.SLASH);
                if (split != null) {
                    String folder = split[split.length - 1];
                    // 文件所在当前目录名称
                    treeNodeDTO.setFolderName(folder);
                }
                if (firstFolder == null) {
                    firstFolder = split[0];
                }
            }
            int index = filePath.lastIndexOf(StrUtil.C_SLASH);
            String fileName = filePath.substring(index + 1);
            // 文件名称
            treeNodeDTO.setName(fileName);
            treeNodeDTOList.add(treeNodeDTO);
        }
        // 判断是否存在一级目录
        List<String> fullFolderList = treeNodeDTOList.stream().map(TreeNodeDTO::getFolderFullName).collect(Collectors.toList());
        if (firstFolder != null && !fullFolderList.contains(firstFolder)) {
            TreeNodeDTO treeNodeDTO = new TreeNodeDTO();
            treeNodeDTO.setFolderName(firstFolder);
            treeNodeDTO.setFolderFullName(firstFolder);
            treeNodeDTOList.add(treeNodeDTO);
        }
        treeNodeDTOList.forEach(treeNodeDTO -> {
            log.info("文件名:{},目录全路径:{},当前目录:{}",treeNodeDTO.getName(),treeNodeDTO.getFolderFullName(),treeNodeDTO.getFolderName());
        });
        return treeNodeDTOList;
    }

}
