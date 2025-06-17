package com.example.rnbogyti.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
@Table(name = "app_user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email; // not needed probably? Put it in anyway, JIC

    private LocalTime time = LocalTime.of(18, 0); // to track user's preferred start time
    /* This is initialized here because of how SpringBoot handles construction.
    Even if there is a constructor with values for a class it always uses the blank
    constructor; IE. If I want every new user to default to a 6pm start time, I have to
    initialize it here. */
    
    
    private LocalDate date = LocalDate.now(); 
    // This will be used to check against today's date to check if timePreference is current or not


    // Constructors

    public User(){}

    public User(String un, String email){
        this.username = un;
        this.email = email;
        this.time = LocalTime.of(18, 0);
        this.date = LocalDate.now();
    }

    // Getters

    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public LocalTime getTime(){
        return time;
    }

    public LocalDate getDate(){
        return date;
    }

    // Setters

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

}
