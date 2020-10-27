package com.example.tryitdiet.utils;

import com.example.tryitdiet.models.Ingredient;
import com.example.tryitdiet.repositories.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepo;

    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String s) {
        return ingredientRepo.findById(Long.parseLong(s)).orElse(null);
    }
}
