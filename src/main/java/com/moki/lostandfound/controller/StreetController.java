package com.moki.lostandfound.controller;


import com.moki.lostandfound.model.Street;
import com.moki.lostandfound.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/street/*")
@CrossOrigin(value = "http://localhost:3000")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @GetMapping("findAll")
    public List<Street> findAll(){
        return streetService.findAll();
    }

    @GetMapping("findById")
    public Street findById(@RequestParam Long id){
        return streetService.findById(id);
    }

    @PostMapping("save")
    public Street save(@RequestBody Street street){
        return streetService.save(street);
    }
}
