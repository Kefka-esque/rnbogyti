package com.example.rnbogyti.entity;

import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String name;

    //Constructors

    public Exercise() {}

    public Exercise(String name){
        this.name = name;
    }

    //Getters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    // Setters

    public void setName(String name){
        this.name = name;
    }
}

