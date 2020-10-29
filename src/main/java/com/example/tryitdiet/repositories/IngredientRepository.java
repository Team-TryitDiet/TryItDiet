package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    public List<Ingredient> findIngreientByName
}