package com.moki.lostandfound.service;

import com.moki.lostandfound.model.City;

import java.util.List;

public interface CityService {
    public City save(City city);
    public List<City> findAll();
    public City findById(Long id);
    public City update(City city);
    public void delete(City city);
}
