package com.example.tryitdiet.models;

import com.example.tryitdiet.models.Post;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = true, length = 12)
    private String phone_number;

    @Column(nullable = false)
    private boolean is_admin;

    @Column(nullable = false)
    private boolean is_banned;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;



    public User() {
    }

    public User(User copy) {
        id = copy.id;
        username = copy.username;
        password = copy.password;
        email = copy.email;
        phone_number = copy.phone_number;
    }


    public User(long id, String username, String email, String password, String phone_number, boolean is_admin,
                boolean is_banned, List<Post> posts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.is_admin = is_admin;
        this.is_banned = is_banned;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isIs_banned() {
        return is_banned;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }
//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }
}

