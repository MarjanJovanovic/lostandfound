package com.moki.lostandfound.service;

import com.moki.lostandfound.model.Category;

import java.util.List;

public interface CategoryService {
    public Category save(Category category);
    public List<Category> findAll();
    public Category findById(Long id);
    public Category update(Category category);
    public void delete(Category category);
}
