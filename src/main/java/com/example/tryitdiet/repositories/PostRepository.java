package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Add in the query method to help with the posts search functionality
    List<Post> findByTitleContaining(String name);
    List<Post> findByTitleStartsWith(String name);
    List<Post> findAllByDiets(List<Diet> diets);
}
