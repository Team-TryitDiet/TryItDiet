package com.example.tryitdiet.repositories;
import com.example.tryitdiet.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
