package com.moki.lostandfound.controller;

import com.moki.lostandfound.model.User;
import com.moki.lostandfound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findById")
    public User findById(@RequestParam Long id){
        return userService.findById(id);
    }

    @PostMapping("save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
}
