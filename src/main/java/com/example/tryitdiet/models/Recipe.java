package com.example.tryitdiet.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column
    private String preparation;

    @Column
    private String notes;

    // Set relationship between Post and Recipe
    @OneToOne(mappedBy = "recipe")
    private Post post;

    // set relationship between Recipes and Diets
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "recipes_diets",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "diet_id")}
    )
    private List<Diet> diets;


    // Set relationship between Recipes and Ingredients
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    @JoinTable(
            name = "recipes_ingredients",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")}
    )
    private List<Ingredient> ingredients;


    // Default Constructor
    public Recipe() {
    }

    // Constructor
    public Recipe(long id, String preparation, String notes) {
        this.id = id;
        this.preparation = preparation;
        this.notes = notes;
    }

    public Recipe(long id, String preparation, String notes, Post post, List<Diet> diets) {
        this.id = id;
        this.preparation = preparation;
        this.notes = notes;
        this.post = post;
        this.diets = diets;
    }

    public Recipe(long id, String preparation, String notes, Post post, List<Diet> diets, List<Ingredient> ingredients) {
        this.id = id;
        this.preparation = preparation;
        this.notes = notes;
        this.post = post;
        this.diets = diets;
        this.ingredients = ingredients;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;

    }
}
