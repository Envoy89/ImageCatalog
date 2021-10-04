package com.theruzil.image_catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FileTable")
public class FileEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String path;
}
