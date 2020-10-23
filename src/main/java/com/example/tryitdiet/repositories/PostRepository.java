package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
