package com.moki.lostandfound.service;

import com.moki.lostandfound.model.Role;
import com.moki.lostandfound.model.User;

import java.util.List;

public interface AuthService {
    User findByEmail(String email);

    List<Role> getRoles();
}