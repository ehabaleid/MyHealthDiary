package com.ehabaleid.healthdiary.service;

import com.ehabaleid.healthdiary.model.nutrition.Food;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodNutritionServiceTest {

    private static FoodNutritionService foodNutritionService;

    @BeforeAll
    public static void setup() {
        foodNutritionService = new FoodNutritionService();

    }

    @Test
    void return_response_given_user_query() throws JSONException, JsonProcessingException {
        List<Food> foods = Arrays.asList(foodNutritionService.fetchNutritionInformation("apple"));
        assertEquals("apple", foods.get(0).getFood_name());
        assertEquals(1, foods.size());
        assertNotNull(foods);
    }

}
