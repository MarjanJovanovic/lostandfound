package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.CityRepo;
import com.moki.lostandfound.dto.CityResponseDto;
import com.moki.lostandfound.model.City;
import com.moki.lostandfound.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    public CityResponseDto save(City city) {
        return mapToDto(cityRepo.save(city));
    }

    private CityResponseDto mapToDto(City city) {
        return new CityResponseDto(city.getId(), city.getName());
    }

    @Override
    public List<CityResponseDto> findAll() {
        return cityRepo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CityResponseDto findById(Long id) {
        return mapToDto(this.getById(id));
    }

    @Override
    public City getById(Long id) {
        Optional<City> cityOptional = cityRepo.findById(id);
        if (cityOptional.isPresent()){
            return cityOptional.get();
        }
        throw new RuntimeException("Searched city doesn't exist (id: " + id + ")");
    }

    @Override
    public CityResponseDto update(City city) {
        Optional<City> cityOptional = cityRepo.findById(city.getId());
        if (cityOptional.isPresent()){
            return mapToDto(cityRepo.save(city));
        }
        throw new RuntimeException("Updated city with the following id doesn't exist: " + city.getId());
    }

    @Override
    public void delete(City city) {
        cityRepo.delete(city);
    }
}
