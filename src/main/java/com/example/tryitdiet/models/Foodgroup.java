package com.example.tryitdiet.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foodgroups")
public class Foodgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    // Set relationship between Ingredients and Foodgroups
    @ManyToMany(mappedBy = "foodgroups")
    List<Ingredient> ingredients;

    // Constructors
    public Foodgroup() {}

    public Foodgroup(long id, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
