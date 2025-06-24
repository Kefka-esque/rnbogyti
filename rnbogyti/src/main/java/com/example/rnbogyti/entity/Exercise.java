package com.example.rnbogyti.entity;

import com.example.rnbogyti.enums.exerciseType;

import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String name;
    private exerciseType type;

    //Constructors

    public Exercise() {}

    public Exercise(String name, exerciseType type) {
        this.name = name;
        this.type = type;
    }

    //Getters

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public exerciseType getType(){
        return type;
    }

    // Setters

    public void setName(String name){
        this.name = name;
    }

    public void setType(exerciseType type){
        this.type = type;
    }
}

