package com.moki.lostandfound.service;

import com.moki.lostandfound.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    public User findByUsername(String username);
    public User save(User user);
    public List<User> findAll();
    public User findById(Long id);
    public User update(User user);
    public void delete(User user);

    Map<String, Object> auth(HttpServletRequest req);

    Map<String, String> login(HttpServletRequest req);

    Map<String, Object> register(Map<String, String> body);

    Map<String, String> loginFail();
}
