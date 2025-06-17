package com.example.rnbogyti.controller.web;

import com.example.rnbogyti.entity.UserExercise;
import com.example.rnbogyti.service.UserExerciseService;
import com.example.rnbogyti.service.UserService;
import com.example.rnbogyti.service.ExerciseService;
import com.example.rnbogyti.entity.User;
import com.example.rnbogyti.entity.Exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user-exercises")
public class UserExerciseWebController {

    @Autowired
    private UserExerciseService userExerciseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public String listUserExercises(Model model) {
        List<UserExercise> allUserExercises = userExerciseService.getAllUserExercises();
        model.addAttribute("userExercises", allUserExercises);
        return "user-exercise-list"; // Your HTML view
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("userExercise", new UserExercise());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("exercises", exerciseService.getAllExercises());
        return "user-exercise-form"; // Your HTML form view
    }

    @PostMapping
    public String saveUserExercise(@RequestParam("userId") Long userId,
                                   @RequestParam("exerciseId") Long exerciseId,
                                   @RequestParam("weight") double weight,
                                   @RequestParam(value = "date", required = false) String dateStr) {

        UserExercise userExercise = new UserExercise();

        // These return the object directly, not Optional, so we don’t call .get()
        User user = userService.getUserById(userId);
        Exercise exercise = exerciseService.getExerciseById(exerciseId);

        userExercise.setUser(user);
        userExercise.setExercise(exercise);
        userExercise.setWeight(weight);

        if (dateStr == null || dateStr.isEmpty()) {
            userExercise.setDate(LocalDate.now());
        } else {
            userExercise.setDate(LocalDate.parse(dateStr));
        }

        userExerciseService.saveOrUpdateUserExercise(user, exercise, weight, LocalDate.now());

        return "redirect:/user-exercises";
    }
}
