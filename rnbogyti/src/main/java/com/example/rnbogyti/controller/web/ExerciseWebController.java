package com.example.rnbogyti.controller.web;

import com.example.rnbogyti.entity.Exercise;
import com.example.rnbogyti.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exercises")
public class ExerciseWebController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "new-exercise";
    }

    @PostMapping
    public String addExercise(@ModelAttribute Exercise exercise) {
        exerciseService.saveExercise(exercise);
        return "redirect:/user-exercises/new";
    }
}
