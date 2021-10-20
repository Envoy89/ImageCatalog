package com.theruzil.image_catalog.controller;

import com.theruzil.image_catalog.entity.FileEntity;
import com.theruzil.image_catalog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file")
    public FileEntity createFile(@RequestParam("file") MultipartFile file) {
        return fileService.saveFile(file);
    }

    @GetMapping("/file")
    public String getFiles() {
        return "test";
    }

    @GetMapping("/file/{id}")
    @ResponseBody
    public ResponseEntity<Resource> getFileById(@PathVariable int id) {
        Resource file =  fileService.getFileById(id);
        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\""
                ).body(file);
    }
}
