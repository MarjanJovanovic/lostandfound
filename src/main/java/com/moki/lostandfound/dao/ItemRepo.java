package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {


}
