package com.example.rnbogyti.controller.web;

import com.example.rnbogyti.entity.User;
import com.example.rnbogyti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserWebController {

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new-user"; // This is your Thymeleaf template
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list"; // Optional if you want to show a list of users
    }
}
