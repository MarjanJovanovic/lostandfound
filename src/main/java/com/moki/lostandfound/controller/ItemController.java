package com.moki.lostandfound.controller;

import com.moki.lostandfound.dto.ItemRequestDto;
import com.moki.lostandfound.dto.ItemResponseDto;
import com.moki.lostandfound.model.Item;
import com.moki.lostandfound.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/*")
@CrossOrigin(value = "http://localhost:3000")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("findAll")
    public List<ItemResponseDto> findAll(){
        return itemService.findAll();
    }

    @GetMapping("findById")
    public ItemResponseDto findById(@RequestParam Long id){
        return itemService.findById(id);
    }

    @PostMapping("save")
    public ItemResponseDto save(@RequestBody ItemRequestDto itemRequestDto){
        return itemService.save(itemRequestDto);
    }

}
