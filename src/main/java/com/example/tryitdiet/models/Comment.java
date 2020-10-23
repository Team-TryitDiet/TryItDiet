package com.example.tryitdiet.models;


import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String body;

// who create the comments
    @ManyToOne @JoinColumn (name = "user_id")
    private User user;

//the post that is commented on
    @ManyToOne @JoinColumn (name = "post_id")
    private Post post;

//    the comment id that reply to certain comments
    @Column(nullable = true)
    private long parentId;

    public Comment() {
    }

    public Comment(long id, String body, User user, Post post, long parentId) {
        this.id = id;
        this.body = body;
        this.user = user;
        this.post = post;
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
