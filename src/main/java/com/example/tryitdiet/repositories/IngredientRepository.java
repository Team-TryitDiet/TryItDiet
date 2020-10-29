package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // Add in the query method to help with the posts search functionality
    List<Ingredient> findByNameContaining(String name);
}