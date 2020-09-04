package com.moki.lostandfound.controller;

import com.moki.lostandfound.dto.CityResponseDto;
import com.moki.lostandfound.model.City;
import com.moki.lostandfound.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city/*")
@CrossOrigin(value = "http://localhost:3000")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("findAll")
    public List<CityResponseDto> findAll(){
        return cityService.findAll();
    }

    @GetMapping("findById")
    public CityResponseDto findById(@RequestParam Long id){
        return cityService.findById(id);
    }

    @PostMapping("save")
    public CityResponseDto save(@RequestBody City category){
        return cityService.save(category);
    }

}
