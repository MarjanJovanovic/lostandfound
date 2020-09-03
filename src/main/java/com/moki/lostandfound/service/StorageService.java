package com.moki.lostandfound.service;

import com.moki.lostandfound.dto.ImageUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;


public interface StorageService {
    ImageUploadResponseDto save(MultipartFile file);

    String load(String filename);

    void deleteAll();

}