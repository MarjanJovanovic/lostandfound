package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dto.ImageUploadResponseDto;
import com.moki.lostandfound.service.StorageService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${storage.directory.path}")
    private String imageStorageDirPath;

    @Override
    public ImageUploadResponseDto save(MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        byte[] imageBytes;
        try {
            imageBytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid image");
        }

        Path path = Paths.get(imageStorageDirPath + fileName);

        if (!Files.isWritable(Path.of(imageStorageDirPath))) {
            throw new RuntimeException(String.format("Couldn't write file to disk. Directory is not writeable: %s", path.toString()));
        }

        try {
            Files.write(path, imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Couldn't write file to disk. File: %s, path: %s", fileName, path.toString()));
        }

        return new ImageUploadResponseDto(fileName, LocalDateTime.now());
    }

    @Override
    public String load(String filename) {
        return imageStorageDirPath + filename;
    }

    @Override
    public void deleteAll() {
        try {
            FileUtils.cleanDirectory(Paths.get(imageStorageDirPath).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
