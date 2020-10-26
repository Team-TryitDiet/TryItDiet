package com.example.tryitdiet.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column
    private String preparation;

    @Column
    private String notes;

    // Set relationship between Post and Recipe
    @OneToOne(mappedBy = "recipe")
    private Post post;

    // Many to Many Relationships
    // Set relationship between Ingredients and Recipes
    @ManyToMany(mappedBy = "recipes")
    private List<Ingredient> ingredients;


    // Default Constructor
    public Recipe() {}

    // Constructor
    public Recipe(long id, String preparation, String notes) {
        this.id = id;
        this.preparation = preparation;
        this.notes = notes;
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
}
