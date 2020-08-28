package com.moki.lostandfound.service;

import com.moki.lostandfound.dto.ItemResponseDto;
import com.moki.lostandfound.model.Item;

import java.util.List;

public interface ItemService {
    public ItemResponseDto save(Item item);
    public List<ItemResponseDto> findAll();
    public ItemResponseDto findById(Long id);
    public Item update(Item item);
    public void delete(Item item);

}
