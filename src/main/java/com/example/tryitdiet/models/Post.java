package com.example.tryitdiet.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(nullable = false, length = 250)
    @NotBlank(message = "Posts must have a title!")
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private Date date;

    // Set relationship between Posts and user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //set relationship between Post and Comments
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    // Set relationship between Post and Recipe
    @OneToOne(cascade = CascadeType.ALL)
    private Recipe recipe;


    //set relationship between Posts and Diets
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "posts_diets",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "diet_id")}
    )
    private List<Diet> diets;

    // Default Constructor
    public Post() {
    }


    // Constructors
    public Post(long id, String title, Date date, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Post(long id, String title, String description, Date date, User user, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.user = user;
        this.comments = comments;
    }

    public Post(long id, String title, String description, Date date, User user, List<Comment> comments, Recipe recipe, List<Diet> diets) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.user = user;
        this.comments = comments;
        this.recipe = recipe;
        this.diets = diets;
    }

    // getter and setter

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
