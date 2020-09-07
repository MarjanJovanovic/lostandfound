package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.ItemRepo;
import com.moki.lostandfound.dto.ImageResponseDto;
import com.moki.lostandfound.dto.ItemRequestDto;
import com.moki.lostandfound.dto.ItemResponseDto;
import com.moki.lostandfound.model.Category;
import com.moki.lostandfound.model.City;
import com.moki.lostandfound.model.Image;
import com.moki.lostandfound.model.Item;
import com.moki.lostandfound.service.CategoryService;
import com.moki.lostandfound.service.CityService;
import com.moki.lostandfound.service.ItemService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Value("${storage.server.path}")
    private String imageServerPath;

    @Override
    public ItemResponseDto save(ItemRequestDto itemRequestDto) {

        City city = cityService.getById(itemRequestDto.getCityId());
        Category category = categoryService.getById(itemRequestDto.getCategoryId());

        Item item = new Item();

        item.setCity(city);
        item.setCategory(category);
        item.setDescription(itemRequestDto.getDescription());
        item.setIsLost(itemRequestDto.getIsLost());
        item.setName(itemRequestDto.getName());
        Image image = new Image();
        image.setItem(item);
        image.setFilename(imageServerPath + itemRequestDto.getImageFileName());
        item.setImages(List.of(image));

        System.out.println("saving new item");

        return mapToDto(itemRepo.save(item));

    }

    @Override
    public Page<ItemResponseDto> findAll(Predicate predicate, Pageable pageable) {
        Page<Item> itemsPage = itemRepo.findAll(predicate, pageable);
        List<ItemResponseDto> itemResponseDtos = mapItemsToDto(itemsPage.getContent());
        PageRequest newPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return new PageImpl<>(itemResponseDtos, newPageable, itemsPage.getTotalPages());
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
    public List<ItemResponseDto> filterByCityAndCategory(Long cityId, Long categoryId) {
        return mapItemsToDto(itemRepo.findByCityAndCategory(cityId, categoryId));
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
        itemResponseDto.setCategory(item.getCategory().getName());
        itemResponseDto.setCity(item.getCity().getName());


        List<ImageResponseDto> imageList = new ArrayList<>();
        for (Image image : item.getImages()) {
            ImageResponseDto imageResponseDto = new ImageResponseDto();
            imageResponseDto.setId(image.getId());
            imageResponseDto.setUrl(image.getFilename());
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
