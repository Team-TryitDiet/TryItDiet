package com.example.tryitdiet.repositories;

import com.example.tryitdiet.models.Foodgroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodgroupRepository extends JpaRepository<Foodgroup, Long> {
}
