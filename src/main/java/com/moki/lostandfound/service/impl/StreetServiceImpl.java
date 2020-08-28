package com.moki.lostandfound.service.impl;

import com.moki.lostandfound.dao.StreetRepo;
import com.moki.lostandfound.model.Street;
import com.moki.lostandfound.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetRepo streetRepo;

    @Override
    public Street save(Street street) {
        return streetRepo.save(street);
    }

    @Override
    public List<Street> findAll() {
        return streetRepo.findAll();
    }

    @Override
    public Street findById(Long id) {
        Optional<Street> streetOptional = streetRepo.findById(id);
        if (streetOptional.isPresent()){
            return streetOptional.get();
        }
        throw new RuntimeException("Searched user doesn't exist (id: " + id + ")");
    }

    @Override
    public Street update(Street street) {
        Optional<Street> streetOptional = streetRepo.findById(street.getId());
        if (streetOptional.isPresent()){
            return streetRepo.save(street);
        }
        throw new RuntimeException("Updated user with the following id doesn't exist: " + street.getId());
    }

    @Override
    public void delete(Street street) {
        streetRepo.delete(street);
    }
}
