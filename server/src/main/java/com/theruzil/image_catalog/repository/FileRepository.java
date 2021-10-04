package com.theruzil.image_catalog.repository;

import com.theruzil.image_catalog.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Integer> {
}
