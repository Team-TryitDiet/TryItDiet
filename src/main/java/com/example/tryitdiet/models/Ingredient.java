package com.example.tryitdiet.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    // Set relationship between Recipes and Ingredients
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

//    @ManyToMany(mappedBy = "ingredients")
//    @JoinTable(
//            name="ingredients_foodgroups",
//            joinColumns = {@JoinColumn(name="recipe_id")},
//            inverseJoinColumns = {@JoinColumn(name="ingredient_id")}
//    )
//    private List<Ingredient> foodgroups;

    // Constructors
    public Ingredient() {}

    public Ingredient(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}