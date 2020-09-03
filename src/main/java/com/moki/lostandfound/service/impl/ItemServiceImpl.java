package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.ItemRepo;
import com.moki.lostandfound.dto.ImageResponseDto;
import com.moki.lostandfound.dto.ItemRequestDto;
import com.moki.lostandfound.dto.ItemResponseDto;
import com.moki.lostandfound.model.*;
import com.moki.lostandfound.service.CategoryService;
import com.moki.lostandfound.service.CityService;
import com.moki.lostandfound.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CityService cityService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public ItemResponseDto save(ItemRequestDto itemRequestDto) {

        City city = cityService.findById(itemRequestDto.getCityId());
        Category category = categoryService.findById(itemRequestDto.getCategoryId());

        Item item = new Item();

        item.setCity(city);
        item.setCategory(category);
        item.setDescription(itemRequestDto.getDescription());
        item.setIsLost(itemRequestDto.getIsLost());
        item.setName(itemRequestDto.getName());

        System.out.println("saving new item");

        return mapToDto(itemRepo.save(item));
    }

    @Override
    public List<ItemResponseDto> findAll() {
        return mapItemsToDto(itemRepo.findAll());
    }

    @Override
    public ItemResponseDto findById(Long id) {
        Optional<Item> itemOptional = itemRepo.findById(id);
        if (itemOptional.isPresent()) {
            return mapToDto(itemOptional.get());
        }
        throw new RuntimeException("Searched item doesn't exist (id: " + id + ")");
    }

    @Override
    public Item update(Item item) {
        Optional<Item> itemOptional = itemRepo.findById(item.getId());
        if (itemOptional.isPresent()) {
            return itemRepo.save(item);
        }
        throw new RuntimeException("Updated item with the following id doesn't exist: " + item.getId());
    }

    @Override
    public void delete(Item item) {
        itemRepo.delete(item);
    }

    private ItemResponseDto mapToDto(Item item) {
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.setDescription(item.getDescription());
        itemResponseDto.setId(item.getId());
        itemResponseDto.setIsLost(item.getIsLost());
        itemResponseDto.setName(item.getName());

        List<ImageResponseDto> imageList = new ArrayList<>();
        for (Image image : item.getImages()) {
            ImageResponseDto imageResponseDto = new ImageResponseDto();
            imageResponseDto.setId(image.getId());
            imageResponseDto.setUrl(image.getUrl());
            imageList.add(imageResponseDto);
        }
        itemResponseDto.setImages(imageList);
        return itemResponseDto;
    }

    private List<ItemResponseDto> mapItemsToDto(List<Item> items) {
        List<ItemResponseDto> responseDtos = new ArrayList<>();
        for (Item item : items) {
            responseDtos.add(mapToDto(item));
        }
        return responseDtos;
    }
}
