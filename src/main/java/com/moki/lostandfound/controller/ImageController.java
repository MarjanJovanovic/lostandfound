package com.moki.lostandfound.controller;

import com.moki.lostandfound.dto.ImageUploadResponseDto;
import com.moki.lostandfound.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image/*")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class ImageController {

    private final StorageService storageService;

    @PostMapping("upload")
    public ImageUploadResponseDto save(@RequestParam("file") MultipartFile file) {
        return storageService.save(file);
    }

    @GetMapping(value = "load/{image}", consumes = MediaType.ALL_VALUE, produces = "image/jpg")
    public FileSystemResource load(@PathVariable("image") String imageName) {
        return new FileSystemResource(storageService.load(imageName));
    }

    @DeleteMapping("delete")
    public void deleteAll() {
        storageService.deleteAll();
    }
}
