package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository <Role, Long> {
}
