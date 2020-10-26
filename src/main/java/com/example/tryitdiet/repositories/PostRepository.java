package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
