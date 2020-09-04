package com.moki.lostandfound.service;

import com.moki.lostandfound.dto.CityResponseDto;
import com.moki.lostandfound.model.City;

import java.util.List;

public interface CityService {
    public CityResponseDto save(City city);
    public List<CityResponseDto> findAll();
    public CityResponseDto findById(Long id);
    public City getById(Long id);
    public CityResponseDto update(City city);
    public void delete(City city);
}
