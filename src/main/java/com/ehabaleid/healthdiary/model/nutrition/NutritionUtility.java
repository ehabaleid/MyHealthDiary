package com.ehabaleid.healthdiary.model.nutrition;

public class NutritionUtility {


    public static float getTotalCalories(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_calories();
        }
        return nutrientSum;
    }

    public static float getTotalProtein(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_protein();
        }
        return nutrientSum;
    }

    public static float getTotalFat(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_total_fat();
        }
        return nutrientSum;
    }

    public static float getTotalSaturatedFat(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_saturated_fat();
        }
        return nutrientSum;
    }

    public static float getTotalTransFat(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            for (Nutrient nutrient : food.getFull_nutrients()) {
                if (nutrient.getNutrientId() == 605) {
                    nutrientSum += nutrient.getNutrientValue();
                }
            }
        }
        return nutrientSum;
    }

    public static float getTotalCholesterol(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_total_fat();
        }
        return nutrientSum;
    }

    public static float getTotalSodium(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_sodium();
        }
        return nutrientSum;
    }

    public static float getTotalCarb(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_total_carbohydrate();
        }
        return nutrientSum;
    }

    public static float getTotalFiber(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_dietary_fiber();
        }
        return nutrientSum;
    }

    public static float getTotalSugar(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_sugars();
        }
        return nutrientSum;
    }

    public static float getTotalPotassium(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            nutrientSum += food.getNf_potassium();
        }
        return nutrientSum;
    }

    public static float getAddedSugar(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            for (Nutrient nutrient : food.getFull_nutrients()) {
                if (nutrient.getNutrientId() == 539) {
                    nutrientSum += nutrient.getNutrientValue();
                }
            }
        }
        return nutrientSum;
    }

    public static float getTotalVitaminD(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            for (Nutrient nutrient : food.getFull_nutrients()) {
                if (nutrient.getNutrientId() == 324) {
                    nutrientSum += nutrient.getNutrientValue();
                }
            }
        }
        nutrientSum = nutrientSum / 40; // convert from IU to mcg
        return nutrientSum;
    }

    public static float getTotalCalcium(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            for (Nutrient nutrient : food.getFull_nutrients()) {
                if (nutrient.getNutrientId() == 301) {
                    nutrientSum += nutrient.getNutrientValue();
                }
            }
        }
        return nutrientSum;
    }


    public static float getTotalIron(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            for (Nutrient nutrient : food.getFull_nutrients()) {
                if (nutrient.getNutrientId() == 303) {
                    nutrientSum += nutrient.getNutrientValue();
                }
            }
        }
        return nutrientSum;
    }

    public static float getTotalCaffeine(Food[] foods) {
        float nutrientSum = 0;
        for (Food food : foods) {
            for (Nutrient nutrient : food.getFull_nutrients()) {
                if (nutrient.getNutrientId() == 262) {
                    nutrientSum += nutrient.getNutrientValue();
                }
            }
        }
        return nutrientSum;
    }

    public static float getProteinInCalories(Food[] foods) {
        return getTotalProtein(foods) * 4;
    }

    public static float getCarbInCalories(Food[] foods) {
        return getTotalCarb(foods) * 4;
    }

    public static float getFatInCalories(Food[] foods) {
        return getTotalFat(foods) * 9;
    }

    public static int getProteinDaleyValueStatus(float protein) {

        return Math.round((protein / 50) * 100);
    }

    public static float getVitaminDDaleyValueStatus(float vitaminD) { //input should be in mcg

        return vitaminD / 20;
    }

    public static float getIronDaleyValueStatus(float iron) {

        return iron / 18;  //returns mg units
    }

    public static float getCalciumDaleyValueStatus(float calcium) {

        return calcium / 1300;  //returns mg units
    }

    public static int getCaloriesDaleyValueStatus(float calories) {

        return Math.round((calories / 2000) * 100);
    }

    public static int getFiberDaleyValueStatus(float fiber) {

        return Math.round((fiber / 28) * 100);
    }

    public static int getPotassiumDaleyValueStatus(float potassium) {

        return Math.round((potassium / 4700) * 100);
    }


}
