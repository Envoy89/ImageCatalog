package com.theruzil.image_catalog.service.Impl;

import com.theruzil.image_catalog.entity.FileEntity;
import com.theruzil.image_catalog.exception.AppException;
import com.theruzil.image_catalog.repository.FileRepository;
import com.theruzil.image_catalog.service.FileService;
import com.theruzil.image_catalog.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final StorageService storageService;

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

        if (file == null) {
            throw new AppException("File not exist");
        }

        return storageService.load(file.getPath());
    }

    @Override
    public List<FileEntity> getFiles(int pageNumber, int pageSize) {
        return fileRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public FileEntity updateFile(FileEntity file) {
        FileEntity newFile = fileRepository.findById(file.getId()).orElse(null);

        if (newFile == null) {
            throw new AppException("File not exist");
        }

        newFile.setName(file.getName());
        newFile.setTags(file.getTags());

        return fileRepository.save(newFile);
    }

    @Override
    public void deleteFile(int id) {
        fileRepository.deleteById(id);
    }
}
