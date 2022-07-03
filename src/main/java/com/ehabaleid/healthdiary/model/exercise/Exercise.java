package com.ehabaleid.healthdiary.model.exercise;

import com.ehabaleid.healthdiary.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Exercise_Log")
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userInputExercise;
    private String exerciseName;
    private float durationMin;
    private int caloriesBurned;
    private float met;  // metabolic equivalent of task
    @ManyToOne
    private User user;
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("user_input")
    public String getUserInputExercise() {
        return userInputExercise;
    }

    @JsonProperty("user_input")
    public void setUserInputExercise(String userInputExercise) {
        this.userInputExercise = userInputExercise;
    }

    @JsonProperty("name")
    public String getExerciseName() {
        return exerciseName;
    }

    @JsonProperty("name")
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @JsonProperty("duration_min")
    public float getDurationMin() {
        return durationMin;
    }

    @JsonProperty("duration_min")
    public void setDurationMin(float durationMin) {
        this.durationMin = durationMin;
    }

    @JsonProperty("nf_calories")
    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    @JsonProperty("nf_calories")
    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public float getMet() {
        return met;
    }

    public void setMet(float met) {
        this.met = met;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
