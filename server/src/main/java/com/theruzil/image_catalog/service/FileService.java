package com.theruzil.image_catalog.service;

import com.theruzil.image_catalog.entity.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileEntity saveFile(MultipartFile file);

    Resource getFileById(int id);
}
