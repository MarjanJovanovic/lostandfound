package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.RoleRepo;
import com.moki.lostandfound.dao.UserRepo;
import com.moki.lostandfound.model.Role;
import com.moki.lostandfound.model.User;
import com.moki.lostandfound.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User with given email wasn't found"));
    }

//    @Override
//    public User findByUsername(String username) {
//        return userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User with given username wasn't found"));
//    }

    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

}