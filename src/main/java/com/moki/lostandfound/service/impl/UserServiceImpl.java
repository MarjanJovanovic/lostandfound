package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.UserRepo;
import com.moki.lostandfound.model.User;
import com.moki.lostandfound.service.RoleService;
import com.moki.lostandfound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.findAll());
        userRepo.save(user);
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("Searched user doesn't exist (username: " + username + ")"));
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new RuntimeException("Searched user doesn't exist (id: " + id + ")");
    }

    @Override
    public User update(User user) {
        Optional<User> userOptional = userRepo.findById(user.getId());
        if (userOptional.isPresent()) {
            return userRepo.save(user);
        }
        throw new RuntimeException("Updated user with the following id doesn't exist: " + user.getId());
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public Map<String, Object> auth(HttpServletRequest req) {

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Map<String, Object> response = new HashMap();

        String username = "";

        if (req.getUserPrincipal() != null) {
            //not authorized
            username = req.getUserPrincipal().getName();
            System.out.println("AUTHOVAN");
        } else {
            System.out.println("NIJE AUTHOVAN");
        }
        response.put("message", "");
        response.put("success", false);
        response.put("type", 1);
        response.put("initLogin", false);
        response.put("username", "");

        if (!authorities.isEmpty() && !authorities.contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
            String role = authorities.toArray()[0].toString();

            // User currUser = findByUsername(username);
            response.put("message", role.toLowerCase());
            response.put("success", true);
            response.put("username", username);
            response.put("type", role.toLowerCase());
        }
        return response;
    }

    @Override
    public Map<String, String> login(HttpServletRequest req) {
        Map<String, String> response = new HashMap();
        System.out.println(req.getUserPrincipal().toString());
        if (req.getUserPrincipal() != null && !req.getUserPrincipal().getName().isBlank()) {
            String username = req.getUserPrincipal().getName();
            response.put("success", "true");
            response.put("message", "all good");
            response.put("userame", username);
            response.put("type", "1");
            System.out.println("successful login");
            return response;
        }
        System.out.println("login failed");

        response.put("success", "false");
        response.put("message", "login failed");
        response.put("type", "0");
        return response;
    }

    @Override
    public Map<String, Object> register(Map<String, String> body) {
        Map<String, Object> response = new HashMap();
        response.put("success", false);
        if (validate(body)) {
            String username = body.get("username");
            String password = body.get("password");
            String firstName = body.get("firstname");
            String lastName = body.get("lastname");
            String email = body.get("email");
            String phone = body.get("phone");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setName(firstName);
            user.setSurname(lastName);
            user.setEmail(email);
            user.setPhoneNumber(phone);
            save(user);
            response.put("success", true);
        }
        return response;
    }

    @Override
    public Map<String, String> loginFail() {
        System.out.println("LOGIN FAIL");
        Map<String, String> response = new HashMap();
        response.put("success", "false");
        response.put("message", "login fail");
        response.put("type", "0");
        return response;
    }

    private boolean validate(Map<String, String> body) {
        return !body.get("username").isBlank() && !body.get("password").isBlank()
                && !body.get("firstname").isBlank() && !body.get("lastname").isBlank()
                && !body.get("email").isBlank() && !body.get("phone").isBlank();
    }

}
