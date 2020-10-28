package com.example.tryitdiet.repositories;
import com.example.tryitdiet.models.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long>{
}

