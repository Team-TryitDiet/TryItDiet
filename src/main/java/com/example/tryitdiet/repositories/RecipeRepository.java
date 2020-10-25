package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}