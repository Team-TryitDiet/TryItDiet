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

    //relationship between recipe and diet
    @ManyToMany(mappedBy = "diets")
    private List<Recipe> recipes;

    //relationship between post and diet
    @ManyToMany(mappedBy = "diets")
    private List<Post> posts;


    public Diet() {
    }

    public Diet(long id, String title, String description, List<Recipe> recipes, List<Post> posts) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.recipes = recipes;
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

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

}
