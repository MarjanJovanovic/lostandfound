package com.moki.lostandfound.service;

import com.moki.lostandfound.dto.ItemRequestDto;
import com.moki.lostandfound.dto.ItemResponseDto;
import com.moki.lostandfound.model.Item;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    public ItemResponseDto save(ItemRequestDto itemRequestDto);

    Page<ItemResponseDto> findAll(Predicate predicate, Pageable pageable);

    public ItemResponseDto findById(Long id);
    public Item update(Item item);
    public void delete(Item item);

    List<ItemResponseDto> filterByCityAndCategory(Long cityId, Long categoryId);
}
