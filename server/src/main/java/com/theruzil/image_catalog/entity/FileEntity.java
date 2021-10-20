package com.theruzil.image_catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "File_Tag_Table",
            joinColumns = { @JoinColumn(name = "file_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private Set<TagEntity> tags = new HashSet<>();
}
