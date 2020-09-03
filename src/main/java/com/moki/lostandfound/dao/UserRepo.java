package com.moki.lostandfound.dao;

import com.moki.lostandfound.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
