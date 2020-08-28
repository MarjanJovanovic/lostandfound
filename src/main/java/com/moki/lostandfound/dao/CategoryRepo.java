package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
