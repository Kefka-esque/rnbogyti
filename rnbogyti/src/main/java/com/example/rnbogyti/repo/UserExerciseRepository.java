package com.example.rnbogyti.repo;

import com.example.rnbogyti.entity.Exercise;
import com.example.rnbogyti.entity.User;
import com.example.rnbogyti.entity.UserExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserExerciseRepository extends JpaRepository<UserExercise, Long> {
    List<UserExercise> findByUserId(Long userId);
    Optional<UserExercise> findByUserAndExercise(User user, Exercise exercise);
    UserExercise findTopByUserIdAndExerciseIdOrderByDateDesc(Long userId, Long exerciseId);
}
