package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}