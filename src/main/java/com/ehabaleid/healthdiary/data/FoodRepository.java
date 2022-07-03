package com.ehabaleid.healthdiary.data;

import com.ehabaleid.healthdiary.model.nutrition.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findByUserId(int id);
    List<Food> findByDateAndUserId(LocalDate date, int id);
}

