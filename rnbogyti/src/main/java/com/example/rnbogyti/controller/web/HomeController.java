package com.example.rnbogyti.controller.web;

import com.example.rnbogyti.entity.Exercise;
import com.example.rnbogyti.entity.User;
import com.example.rnbogyti.entity.UserExercise;
import com.example.rnbogyti.service.ExerciseService;
import com.example.rnbogyti.service.UserExerciseService;
import com.example.rnbogyti.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private UserExerciseService userExerciseService;
    
    @GetMapping("/")
    public String selectUser(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "select-user";
    }

    // The old home page, now per-user so the time functions can be nicer. Whole thing is still WIP though.
    // Some pages that should point to user hoem still point to the "/" home; but this should be fixed in the future 
    // once I introduce http sessions; there will be a redirect to user home if a user id is already in the session 
    @GetMapping("/user/home/{id}")
    public String home(@PathVariable Long id, Model model) {
        // 1. Get list of users and exercises
        List<User> users = userService.getAllUsers();
        List<Exercise> exercises = exerciseService.getAllExercises();

        // 2. Build a map: exerciseName -> (userName -> weight)
        Map<String, Map<String, Double>> exerciseUserWeights = new LinkedHashMap<>();

        for (Exercise exercise : exercises) {
            Map<String, Double> userWeights = new HashMap<>();
            for (User user : users) {
                // Get the latest UserExercise entry for this user & exercise
                UserExercise latest = userExerciseService.getLatestByUserAndExercise(user.getId(), exercise.getId());
                if (latest != null) {
                    userWeights.put(user.getUsername(), latest.getWeight());
                } else {
                    userWeights.put(user.getUsername(), null);
                }
            }
            exerciseUserWeights.put(exercise.getName(), userWeights);
        }

        User user = userService.getUserById(id);

        // Attributes the page uses
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        model.addAttribute("exerciseUserWeights", exerciseUserWeights);
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("message", "Welcome to the Rainbow Boys Gym Time app!");
        return "home";
    } 
}
