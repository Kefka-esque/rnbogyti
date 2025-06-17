package com.example.rnbogyti.service;

import com.example.rnbogyti.entity.User;
import com.example.rnbogyti.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.Optional;
// This was causing issues, it is bye bye for now

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
            .map(existingUser -> {
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setEmail(updatedUser.getEmail());
                return userRepository.save(existingUser);
            })
            .orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
