package com.example.tryitdiet.repositories;
import com.example.tryitdiet.models.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long>{
    List<Diet> findByTitleContaining(String title);
    Diet findDietByTitle(String title);
}

