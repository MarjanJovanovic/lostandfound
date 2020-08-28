package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.UserRepo;
import com.moki.lostandfound.model.User;
import com.moki.lostandfound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()){
            return userOptional.get();
        }
        throw new RuntimeException("Searched user doesn't exist (id: " + id + ")");
    }

    @Override
    public User update(User user) {
        Optional<User> userOptional = userRepo.findById(user.getId());
        if (userOptional.isPresent()){
            return userRepo.save(user);
        }
        throw new RuntimeException("Updated user with the following id doesn't exist: " + user.getId());
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }
}
