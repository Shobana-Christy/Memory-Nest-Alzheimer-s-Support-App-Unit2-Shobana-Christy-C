package com.care4memory.memorynest.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AlbumDTO {
    private String name;
    private List<MultipartFile> files;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
