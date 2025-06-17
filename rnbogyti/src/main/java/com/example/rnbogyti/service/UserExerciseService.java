package com.example.rnbogyti.service;

import com.example.rnbogyti.entity.Exercise;
import com.example.rnbogyti.entity.User;
import com.example.rnbogyti.entity.UserExercise;
import com.example.rnbogyti.repo.UserExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public UserExercise saveOrUpdateUserExercise(User user, Exercise exercise, double weight, LocalDate date) {
        Optional<UserExercise> existing = userExerciseRepository.findByUserAndExercise(user, exercise);

        UserExercise userExercise = existing.orElse(new UserExercise());
        userExercise.setUser(user);
        userExercise.setExercise(exercise);
        userExercise.setWeight(weight);
        userExercise.setDate(date != null ? date : LocalDate.now());

        return userExerciseRepository.save(userExercise);
    }
}
    
