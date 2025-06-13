package com.example.rnbogyti.service;

import com.example.rnbogyti.entity.Exercise;
import com.example.rnbogyti.repo.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }
    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
