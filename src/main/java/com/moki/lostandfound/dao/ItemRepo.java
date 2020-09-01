package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.Item;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {

//    @Override
//    <S extends Item> Page<S> findAll(Example<S> example, Pageable pageable);
}
