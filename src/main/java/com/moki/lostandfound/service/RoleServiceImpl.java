package com.moki.lostandfound.service;

import com.moki.lostandfound.dao.RoleRepo;
import com.moki.lostandfound.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(roleRepo.findAll());
    }

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void delete(Role role) {

    }
}
