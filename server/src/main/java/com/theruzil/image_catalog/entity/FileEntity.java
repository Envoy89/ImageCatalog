package com.theruzil.image_catalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file_table")
public class FileEntity {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String path;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "file_tag_table",
            joinColumns = { @JoinColumn(name = "file_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private Set<TagEntity> tags = new HashSet<>();
}
