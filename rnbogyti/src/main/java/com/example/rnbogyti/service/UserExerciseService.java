package com.example.rnbogyti.service;

import com.example.rnbogyti.entity.UserExercise;
import com.example.rnbogyti.repo.UserExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExerciseService {

    @Autowired
    private UserExerciseRepository userExerciseRepository;

    public List<UserExercise> getAllUserExercises() {
        return userExerciseRepository.findAll();
    }

    public UserExercise saveUserExercise(UserExercise userExercise) {
        return userExerciseRepository.save(userExercise);
    }

    public List<UserExercise> getExercisesByUserId(Long userId) {
        return userExerciseRepository.findByUserId(userId);
    }

    public UserExercise getLatestByUserAndExercise(Long userId, Long exerciseId) {
    return userExerciseRepository.findTopByUserIdAndExerciseIdOrderByDateDesc(userId, exerciseId);
}
}
