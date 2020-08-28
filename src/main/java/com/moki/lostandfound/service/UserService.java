package com.moki.lostandfound.service;

import com.moki.lostandfound.model.User;

import java.util.List;

public interface UserService {
    public User save(User user);
    public List<User> findAll();
    public User findById(Long id);
    public User update(User user);
    public void delete(User user);
}
