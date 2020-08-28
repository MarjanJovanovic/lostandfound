package com.moki.lostandfound.service;

import com.moki.lostandfound.model.Image;

import java.util.List;

public interface ImageService {
    public Image save(Image image);
    public List<Image> findAll();
    public Image findById(Long id);
    public Image update(Image image);
    public void delete(Image image);
}
