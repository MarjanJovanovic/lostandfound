package com.moki.lostandfound.service;

import com.moki.lostandfound.model.Role;

import java.util.List;

public interface RoleService {
    public Role save(Role role);
    public List<Role> findAll();
    public Role findById(Long id);
    public Role update(Role role);
    public void delete(Role role);
}
