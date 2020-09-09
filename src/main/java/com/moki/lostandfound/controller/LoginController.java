package com.moki.lostandfound.controller;


import com.moki.lostandfound.model.User;
import com.moki.lostandfound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public Map<String, String> login(HttpServletRequest req) {
        return userService.login(req);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        return userService.register(body);
    }

    @GetMapping("loginfail")
    public Map<String, String> loginFail() {
        return userService.loginFail();
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest req) {
        String username = req.getUserPrincipal().getName();
        System.out.println(username + " logged out");
    }

    @GetMapping("/user")
    public Map<String, Object> auth(HttpServletRequest req) {
        return userService.auth(req);
    }

}
