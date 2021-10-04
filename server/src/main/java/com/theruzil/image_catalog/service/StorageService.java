package com.theruzil.image_catalog.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();

    String save(MultipartFile file);

    Resource load(String fileName);
}
