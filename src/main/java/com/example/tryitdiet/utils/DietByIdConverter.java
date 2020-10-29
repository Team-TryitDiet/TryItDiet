package com.example.tryitdiet.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.repositories.DietRepository;

@Component
public class DietByIdConverter implements Converter<String, Diet> {

    private DietRepository dietRepo;

    @Autowired
    public DietByIdConverter(DietRepository dietRepo) {
        this.dietRepo = dietRepo;
    }

    @Override
    public Diet convert(String id) {
        // parse the String id to a Long
        return dietRepo.findById(Long.parseLong(id)).orElse(null);
    }

}
