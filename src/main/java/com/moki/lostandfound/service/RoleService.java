package com.moki.lostandfound.service;

import com.moki.lostandfound.model.Role;

import java.util.Set;

public interface RoleService {
    public Role save(Role role);
    public Set<Role> findAll();
    public Role findById(Long id);
    public Role update(Role role);
    public void delete(Role role);
}
