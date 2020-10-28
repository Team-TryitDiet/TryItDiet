package com.example.tryitdiet.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="diets")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToMany(mappedBy = "diets")
    private List<Recipe> recipes;

//    @ManyToMany(mappedBy = "ingredients")
//    private List<Ingredient> ingredients;

    public Diet() {
    }

    public Diet(long id, String title, String description, List<Recipe> recipes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.recipes = recipes;
    }

//    public Diet(long id, String title, String description, List<Recipe> recipes, List<Ingredient> ingredients) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.recipes = recipes;
//        this.ingredients = ingredients;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }
}
