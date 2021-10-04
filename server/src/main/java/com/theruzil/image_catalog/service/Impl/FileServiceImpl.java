package com.theruzil.image_catalog.service.Impl;

import com.theruzil.image_catalog.entity.FileEntity;
import com.theruzil.image_catalog.repository.FileRepository;
import com.theruzil.image_catalog.service.FileService;
import com.theruzil.image_catalog.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;
    private StorageService storageService;

    @Autowired
    public FileServiceImpl(
            FileRepository fileRepository,
            StorageService storageService
    ) {
        this.fileRepository = fileRepository;
        this.storageService = storageService;
    }

    @Override
    public FileEntity saveFile(MultipartFile file) {
        String path = storageService.save(file);

        FileEntity newFile = new FileEntity();
        newFile.setName(file.getName());
        newFile.setPath(path);

        return fileRepository.save(newFile);
    }

    @Override
    public Resource getFileById(int id) {
        FileEntity file = fileRepository.findById(id).orElse(null);
        return storageService.load(file.getPath());
    }
}
