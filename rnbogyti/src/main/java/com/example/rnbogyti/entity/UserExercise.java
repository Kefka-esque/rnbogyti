package com.example.rnbogyti.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_exercise", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "exercise_if"})
})
public class UserExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private double weight;

    private LocalDate date; //Track when the weight was logged? Might be good?

    //Constructors

    public UserExercise(){}

    public UserExercise(User user, Exercise exercise, double weight, LocalDate date){
        this.user = user;
        this.exercise = exercise;
        this.weight = weight;
        this.date = date;
    }

    // Getters

    public Long getId(){
        return id;
    }

    public User getUser(){
        return user;
    }

    public Exercise getExercise(){
        return exercise;
    }

    public double getWeight(){
        return weight;
    }

    public LocalDate getDate(){
        return date;
    }

    // Setters

    public void setId(Long id){
        this.id = id;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setExercise(Exercise exercise){
        this.exercise = exercise;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
}
