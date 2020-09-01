package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.CityRepo;
import com.moki.lostandfound.model.City;
import com.moki.lostandfound.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    public City save(City city) {
        return cityRepo.save(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    @Override
    public City findById(Long id) {
        Optional<City> cityOptional = cityRepo.findById(id);
        if (cityOptional.isPresent()){
            return cityOptional.get();
        }
        throw new RuntimeException("Searched city doesn't exist (id: " + id + ")");
    }

    @Override
    public City update(City city) {
        Optional<City> cityOptional = cityRepo.findById(city.getId());
        if (cityOptional.isPresent()){
            return cityRepo.save(city);
        }
        throw new RuntimeException("Updated city with the following id doesn't exist: " + city.getId());
    }

    @Override
    public void delete(City city) {
        cityRepo.delete(city);
    }
}
