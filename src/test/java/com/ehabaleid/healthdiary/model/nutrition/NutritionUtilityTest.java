package com.ehabaleid.healthdiary.model.nutrition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutritionUtilityTest {

    Food[] foods  = {
            new Food(1, "apple", null, 1,
                    null, 100, 100, 10,
                    10,10, 10, 10,
                    10, 10, 10, 10, null, null),

            new Food(2, "orange", null, 1,
                    null, 111, 111, 11,
                    11,11, 11, 11,
                    11, 11, 11, 11, null, null)};


    @Test
    void getTotalCalories() {
        assertEquals(211, NutritionUtility.getTotalCalories(foods));
    }

    @Test
    void getTotalProtein() {
        assertEquals(21, NutritionUtility.getTotalProtein(foods));

    }

    @Test
    void getTotalFat() {
        assertEquals(21, NutritionUtility.getTotalFat(foods));

    }

    @Test
    void getTotalSaturatedFat() {
        assertEquals(21, NutritionUtility.getTotalSaturatedFat(foods));

    }

    @Test
    void getTotalCholesterol() {
        assertEquals(21, NutritionUtility.getTotalCholesterol(foods));

    }

    @Test
    void getTotalSodium() {
        assertEquals(21, NutritionUtility.getTotalSodium(foods));

    }

    @Test
    void getTotalCarb() {
        assertEquals(21, NutritionUtility.getTotalCarb(foods));

    }

    @Test
    void getTotalFiber() {
        assertEquals(21, NutritionUtility.getTotalFiber(foods));

    }

    @Test
    void getTotalSugar() {
        assertEquals(21, NutritionUtility.getTotalSugar(foods));

    }

    @Test
    void getTotalPotassium() {
        assertEquals(21, NutritionUtility.getTotalPotassium(foods));

    }

    @Test
    void getAddedSugar() {

    }

    @Test
    void getTotalVitaminD() {
    }

    @Test
    void getTotalCalcium() {
    }

    @Test
    void getTotalIron() {
    }

    @Test
    void getTotalCaffeine() {
    }

    @Test
    void getProteinInCalories() {
    }

    @Test
    void getCarbInCalories() {
    }

    @Test
    void getFatInCalories() {
    }

    @Test
    void getProteinDaleyValueStatus() {
    }

    @Test
    void getVitaminDDaleyValueStatus() {
    }

    @Test
    void getIronDaleyValueStatus() {
    }

    @Test
    void getCalciumDaleyValueStatus() {
    }

    @Test
    void getCaloriesDaleyValueStatus() {
    }

    @Test
    void getFiberDaleyValueStatus() {
    }

    @Test
    void getPotassiumDaleyValueStatus() {
    }
}