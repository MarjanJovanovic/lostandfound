package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository <City, Long> {
}
