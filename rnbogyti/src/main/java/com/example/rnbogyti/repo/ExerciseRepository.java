package com.example.rnbogyti.repo;

import com.example.rnbogyti.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    // You can add custom queries here if needed later
}
