package com.theruzil.image_catalog.service.Impl;

import com.theruzil.image_catalog.exception.AppException;
import com.theruzil.image_catalog.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${mainFilePath}")
    private Path root;

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    @Override
    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new AppException("Could not initial main folder", e);
        }
    }

    @Override
    public String save(MultipartFile file) {
        try {
            Path path = this.root.resolve(file.getOriginalFilename());

            File newFile = new File(path.toUri().getPath());
            try (
                    FileOutputStream outputStream = new FileOutputStream(newFile, false);
                    InputStream inputStream = file.getInputStream()
            ) {
                int read;
                byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }

            return path.toUri().getPath();
        } catch (IOException e) {
            throw new AppException("Could not save file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            Path file = root.resolve(fileName);

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new AppException("Could not read file " + fileName);
            }
        } catch (IOException e) {
            throw new AppException("Could not read file " + fileName, e);
        }
    }
}
