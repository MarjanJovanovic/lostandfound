package com.moki.lostandfound.controller;

import com.moki.lostandfound.dao.ItemRepo;
import com.moki.lostandfound.dto.ItemRequestDto;
import com.moki.lostandfound.dto.ItemResponseDto;
import com.moki.lostandfound.model.Item;
import com.moki.lostandfound.service.ItemService;
import com.moki.lostandfound.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/item/*")
@CrossOrigin(value = "http://localhost:3000")
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @GetMapping("findAll")
//    public List<ItemResponseDto> findAll(){
//        return itemService.findAll();
//    }

    @GetMapping("/findAll")
    public Page<ItemResponseDto> findAll(@QuerydslPredicate(root = Item.class, bindings = ItemRepo.class) Predicate predicate,
                                         @PageableDefault(size = 20) Pageable pageable) {
        return ItemServiceImpl.findAll(predicate, pageable);
    }

    @GetMapping("findById")
    public ItemResponseDto findById(@RequestParam Long id) {
        return itemService.findById(id);
    }

    @GetMapping("filterByCityAndCategory")
    public List<ItemResponseDto> filterByCityAndCategory(@RequestParam Long cityId, @RequestParam Long categoryId) {
        return itemService.filterByCityAndCategory(cityId, categoryId);
    }

    @PostMapping("save")
    public ItemResponseDto save(@RequestBody ItemRequestDto itemRequestDto) {
        return itemService.save(itemRequestDto);
    }

}
