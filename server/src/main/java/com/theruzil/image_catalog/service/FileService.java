package com.theruzil.image_catalog.service;

import com.theruzil.image_catalog.entity.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileEntity saveFile(MultipartFile file);

    Resource getFileById(int id);

    List<FileEntity> getFiles(int pageNumber, int pageSize);

    FileEntity updateFile(FileEntity file);

    void deleteFile(int id);
}
