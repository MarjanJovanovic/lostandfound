package com.moki.lostandfound.service;

import com.moki.lostandfound.model.Street;

import java.util.List;

public interface StreetService {
    public Street save(Street street);
    public List<Street> findAll();
    public Street findById(Long id);
    public Street update(Street street);
    public void delete(Street street);
}
