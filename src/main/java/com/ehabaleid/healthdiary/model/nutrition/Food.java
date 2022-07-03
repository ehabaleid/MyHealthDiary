package com.ehabaleid.healthdiary.model.nutrition;

import com.ehabaleid.healthdiary.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Food_Log")
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String food_name;
    private String brand_name;
    private String serving_unit;
    private String meal_type;
    private int serving_qty;
    private float serving_weight_grams;
    private float nf_calories;
    private float nf_total_fat;
    private float nf_saturated_fat;
    private float nf_cholesterol;
    private float nf_sodium;
    private float nf_total_carbohydrate;
    private float nf_dietary_fiber;
    private float nf_sugars;
    private float nf_protein;
    private float nf_potassium;
    @Transient
    private List<Nutrient> full_nutrients;
    @Transient
    private FoodPhoto photo;
    @ManyToOne
    private User user;
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    public Food() {
    }

    public Food(int id, String food_name, String brand_name, int serving_qty, String serving_unit, float serving_weight_grams, float nf_calories, float nf_total_fat, float nf_saturated_fat, float nf_cholesterol, float nf_sodium, float nf_total_carbohydrate, float nf_dietary_fiber, float nf_sugars, float nf_protein, float nf_potassium, String meal_type, User user) {
        this.id = id;
        this.food_name = food_name;
        this.brand_name = brand_name;
        this.serving_qty = serving_qty;
        this.serving_unit = serving_unit;
        this.serving_weight_grams = serving_weight_grams;
        this.nf_calories = nf_calories;
        this.nf_total_fat = nf_total_fat;
        this.nf_saturated_fat = nf_saturated_fat;
        this.nf_cholesterol = nf_cholesterol;
        this.nf_sodium = nf_sodium;
        this.nf_total_carbohydrate = nf_total_carbohydrate;
        this.nf_dietary_fiber = nf_dietary_fiber;
        this.nf_sugars = nf_sugars;
        this.nf_protein = nf_protein;
        this.nf_potassium = nf_potassium;
        this.meal_type = meal_type;
        this.user = user;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getServing_qty() {
        return serving_qty;
    }

    public void setServing_qty(int serving_qty) {
        this.serving_qty = serving_qty;
    }

    public String getServing_unit() {
        return serving_unit;
    }

    public void setServing_unit(String serving_unit) {
        this.serving_unit = serving_unit;
    }

    public float getServing_weight_grams() {
        return serving_weight_grams;
    }

    public void setServing_weight_grams(float serving_weight_grams) {
        this.serving_weight_grams = serving_weight_grams;
    }

    public float getNf_calories() {
        return nf_calories;
    }

    public void setNf_calories(float nf_calories) {
        this.nf_calories = nf_calories;
    }

    public float getNf_total_fat() {
        return nf_total_fat;
    }

    public void setNf_total_fat(float nf_total_fat) {
        this.nf_total_fat = nf_total_fat;
    }

    public float getNf_saturated_fat() {
        return nf_saturated_fat;
    }

    public void setNf_saturated_fat(float nf_saturated_fat) {
        this.nf_saturated_fat = nf_saturated_fat;
    }

    public float getNf_cholesterol() {
        return nf_cholesterol;
    }

    public void setNf_cholesterol(float nf_cholesterol) {
        this.nf_cholesterol = nf_cholesterol;
    }

    public float getNf_sodium() {
        return nf_sodium;
    }

    public void setNf_sodium(float nf_sodium) {
        this.nf_sodium = nf_sodium;
    }

    public float getNf_total_carbohydrate() {
        return nf_total_carbohydrate;
    }

    public void setNf_total_carbohydrate(float nf_total_carbohydrate) {
        this.nf_total_carbohydrate = nf_total_carbohydrate;
    }

    public float getNf_dietary_fiber() {
        return nf_dietary_fiber;
    }

    public void setNf_dietary_fiber(float nf_dietary_fiber) {
        this.nf_dietary_fiber = nf_dietary_fiber;
    }

    public float getNf_sugars() {
        return nf_sugars;
    }

    public void setNf_sugars(float nf_sugars) {
        this.nf_sugars = nf_sugars;
    }

    public float getNf_protein() {
        return nf_protein;
    }

    public void setNf_protein(float nf_protein) {
        this.nf_protein = nf_protein;
    }

    public float getNf_potassium() {
        return nf_potassium;
    }

    public void setNf_potassium(float nf_potassium) {
        this.nf_potassium = nf_potassium;
    }

    public List<Nutrient> getFull_nutrients() {
        return full_nutrients;
    }

    public void setFull_nutrients(List<Nutrient> full_nutrients) {
        this.full_nutrients = full_nutrients;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public FoodPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(FoodPhoto photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

