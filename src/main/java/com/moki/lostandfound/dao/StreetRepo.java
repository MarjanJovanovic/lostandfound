package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepo extends JpaRepository <Street, Long> {
}
