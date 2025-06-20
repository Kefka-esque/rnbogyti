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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private UserExerciseService userExerciseService;
    
    @GetMapping("/")
    public String selectUser(Model model, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null){
            return "redirect:/user/home/" + userId;
        }

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "select-user";
    }

    @GetMapping("/user/home/{id}")
    public String home(@PathVariable Long id, Model model, HttpSession session) {
        
        // Sets the user id so that the sign in page at landing won't display anymore
        session.setAttribute("userId", id);

        // We'll need a list of users for a few different things on this page
        List<User> users = userService.getAllUsers();

       
        // Logic to display the time chart, or not, if all users have confirmed a time
        boolean allConfirmed = true;
        LocalTime agreedTime = null;

        for (User user : users){
            if (user.getDate() == null || ! user.getDate().equals(LocalDate.now()) || user.getTime() == null){
                allConfirmed = false;
                break;
            }
            if (agreedTime == null){
                agreedTime = user.getTime();
            } else if (! agreedTime.equals(user.getTime())){
                allConfirmed = false;
                break;
            }
        }

        // Logic to display the date in a pretty way that makes my brain happy
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMM d");
        String prettyDate = today.format(formatter);

        // Proper grammar separates us from the animals:
        int day = today.getDayOfMonth();
        String suffix;
        if (day >= 11 && day <= 13) {
            suffix = "th";
        } else {
            switch (day % 10) {
                case 1: suffix = "st"; break;
                case 2: suffix = "nd"; break;
                case 3: suffix = "rd"; break;
                default: suffix = "th";
            }
        }

        prettyDate += suffix;

        // Below is logic for building and displaying the weght reference chart
        // 1. Get list of users and exercises
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
        model.addAttribute("today", today);
        model.addAttribute("prettyDate", prettyDate);
        model.addAttribute("allConfirmed", allConfirmed);
        model.addAttribute("agreedTime", agreedTime);
        return "home";
    } 

    @PostMapping("/user/{id}/confirm-time")
    public String confirmTime(@RequestParam Long userId, @RequestParam String time, HttpSession session) {
        User user = userService.getUserById(userId);

        if ("None".equals(time)) {
            user.setTime(null);
        } else {
            user.setTime(LocalTime.parse(time));
        }

        user.setDate(LocalDate.now());
        userService.saveUser(user);

        return "redirect:/user/home/" + userId;
    }
    
}
