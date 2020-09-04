package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.CategoryRepo;
import com.moki.lostandfound.dto.CategoryResponseDto;
import com.moki.lostandfound.model.Category;
import com.moki.lostandfound.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryResponseDto save(Category category) {
        Category savedCategory = categoryRepo.save(category);
        return new CategoryResponseDto(savedCategory.getId(), savedCategory.getName());
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }


    private CategoryResponseDto mapToDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return mapToDto(getById(id));
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (categoryOptional.isPresent()){
            return categoryOptional.get();
        }
        throw new RuntimeException("Searched category doesn't exist (id: " + id + ")");    }

    @Override
    public Category update(Category category) {
        Optional<Category> categoryOptional = categoryRepo.findById(category.getId());
        if (categoryOptional.isPresent()){
            return categoryRepo.save(category);
        }
        throw new RuntimeException("Updated category with the following id doesn't exist: " + category.getId());
    }

    @Override
    public void delete(Category category) {
        categoryRepo.delete(category);
    }
}
