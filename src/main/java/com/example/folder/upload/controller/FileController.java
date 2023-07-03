package com.example.folder.upload.controller;

import com.example.folder.upload.entity.TreeNodeDTO;
import com.example.folder.upload.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    @RequestMapping(value = "/folderUpload", method = RequestMethod.POST)
    public List<TreeNodeDTO> uploadFolder(MultipartFile[] multipartFiles) {
        return fileService.folderUpload(multipartFiles);
    }
}
