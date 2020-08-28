package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository <Image, Long> {
}
