package com.moki.lostandfound.controller;

import com.moki.lostandfound.dto.CategoryResponseDto;
import com.moki.lostandfound.model.Category;
import com.moki.lostandfound.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/*")
@CrossOrigin(value = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("findAll")
    public List<CategoryResponseDto> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("findById")
    public CategoryResponseDto findById(@RequestParam Long id){
        return categoryService.findById(id);
    }

    @PostMapping("save")
    public CategoryResponseDto save(@RequestBody Category category){
        return categoryService.save(category);
    }

}
