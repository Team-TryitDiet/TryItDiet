package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.models.Ingredient;
import com.example.tryitdiet.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // Method to get list of Recipe by ingredients
    List<Recipe> findAllByIngredientsIn(List<Ingredient> ingredients);
    List<Recipe> findAllByDiets(List<Diet> diets);
}