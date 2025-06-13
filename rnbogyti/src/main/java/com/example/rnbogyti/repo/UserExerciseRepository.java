package com.example.rnbogyti.repo;

import com.example.rnbogyti.entity.UserExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExerciseRepository extends JpaRepository<UserExercise, Long> {
    List<UserExercise> findByUserId(Long userId);
    UserExercise findTopByUserIdAndExerciseIdOrderByDateDesc(Long userId, Long exerciseId);
}
