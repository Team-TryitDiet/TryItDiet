package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.User;
import net.bytebuddy.implementation.bind.annotation.FieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByUsername(String username);
    List<User> findAllByEmail(String email);
}