package com.moki.lostandfound.service;

import com.moki.lostandfound.dto.CategoryResponseDto;
import com.moki.lostandfound.model.Category;

import java.util.List;

public interface CategoryService {
    public CategoryResponseDto save(Category category);
    public List<CategoryResponseDto> findAll();
    public CategoryResponseDto findById(Long id);
    public Category getById(Long id);
    public Category update(Category category);
    public void delete(Category category);
}
