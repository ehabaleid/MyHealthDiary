package com.ehabaleid.healthdiary.controller;

import com.ehabaleid.healthdiary.model.exercise.Exercise;
import com.ehabaleid.healthdiary.model.memory.Memory;
import com.ehabaleid.healthdiary.model.nutrition.Food;
import com.ehabaleid.healthdiary.model.nutrition.NutritionUtility;
import com.ehabaleid.healthdiary.model.user.User;
import com.ehabaleid.healthdiary.model.user.UserDTO;
import com.ehabaleid.healthdiary.service.AppUserDetailsService;
import com.ehabaleid.healthdiary.service.ExerciseService;
import com.ehabaleid.healthdiary.service.FoodNutritionService;
import com.ehabaleid.healthdiary.service.MemoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Controller
public class AppController {

	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Autowired
	private FoodNutritionService foodNutritionService;
	@Autowired
	private MemoryService memoryService;
	@Autowired
	private ExerciseService exerciseService;

	@RequestMapping("/")
	public String handleDefaultRouteMapping() {
		return "dashboard";
	}

	@GetMapping("/register")
	public String displayRegistration() {
		return "register";
	}

	@GetMapping("/login")
	public String displayLogin() {
		return "login";
	}

	@GetMapping("/forgotpassword")
	public String displayForgotPassword() {
		return "forgot-password";
	}

	@GetMapping("/dashboard")
	public String displayGetRequest(ModelMap modelMap, Principal principal) {
		setUserNameToModelMap(modelMap, principal);
		setDashboardDataToModelMap(modelMap, principal, LocalDate.now());

		return "dashboard";
	}

	@PostMapping("/dashboard")
	public String dashboardPostRequest(@RequestParam String date, ModelMap modelMap, Principal principal) {
		setUserNameToModelMap(modelMap, principal);
		setDashboardDataToModelMap(modelMap, principal, LocalDate.parse(date));
		return "dashboard";
	}

	@GetMapping("/logmemory")
	public String handleMemoryLogGetRequest(ModelMap modelMap, Principal principal) {
		setUserNameToModelMap(modelMap, principal);

		return "logmemory";
	}

	@PostMapping("/logmemory")
	public String handleMemoryPostRequest(@RequestParam String userMemoryPost, ModelMap modelMap, Principal principal) {
		setUserNameToModelMap(modelMap, principal);

		if (userMemoryPost == null || userMemoryPost.isEmpty() || userMemoryPost.trim().equals("")) {
			modelMap.put("isUserInputValid", false);
		} else {
			modelMap.put("isUserInputValid", true);
			Memory userMemoryLog = new Memory(userMemoryPost);
			setPrincipalToMemory(userMemoryLog, principal);
			memoryService.saveUserMemoryToDatabase(userMemoryLog);
		}

		return "logmemory";
	}

	@GetMapping("/logfood")
	public String handleFoodGetRequest(ModelMap modelMap, Principal principal) {
		setUserNameToModelMap(modelMap, principal);

		return "logfood";
	}


	@PostMapping("/logfood")
	public String handleFoodPostRequest(@RequestParam String foodQuery, ModelMap modelMap, Principal principal) throws JSONException, JsonProcessingException {
		setUserNameToModelMap(modelMap, principal);
		Food[] userFoods = foodNutritionService.fetchNutritionInformation(foodQuery);

		if (userFoods == null) {
			modelMap.put("isUserInputValid", false);
		} else {
			modelMap.put("isUserInputValid", true);
			addFoodNutritionToModelMap(userFoods, modelMap);
			setPrincipalToFood(userFoods, principal);
			foodNutritionService.saveUserFoodsToDatabase(userFoods);
		}

		return "logfood";
	}

	@GetMapping("/logexercise")
	public String handleExerciseGetRequest(ModelMap modelMap, Principal principal) {
		setUserNameToModelMap(modelMap, principal);

		return "logexercise";
	}


	@PostMapping("/logexercise")
	public String handleExercisePostRequest(@RequestParam String exerciseQuery, ModelMap modelMap, Principal principal) throws JSONException, JsonProcessingException {
		setUserNameToModelMap(modelMap, principal);
		Exercise[] userExercises = exerciseService.fetchExerciseInformation(exerciseQuery);

		if (userExercises == null || userExercises.length == 0) {
			modelMap.put("isUserInputValid", false);
		} else {
			int totalCaloriesBurned = Arrays.asList(userExercises).stream().mapToInt(Exercise::getCaloriesBurned).sum();

			modelMap.put("totalCaloriesBurned", totalCaloriesBurned);
			modelMap.put("isUserInputValid", true);
			modelMap.put("userExercises", userExercises);
			setPrincipalToExercise(userExercises, principal);
			exerciseService.saveUserExerciseToDatabase(userExercises);
		}
		return "logexercise";
	}

	@GetMapping("/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserDTO getUser(@PathVariable int id) throws JsonProcessingException {
		return new UserDTO(appUserDetailsService.getUserById(id),
				foodNutritionService.getTotalCaloriesConsumedByUserId(id));

	}

	public void addFoodNutritionToModelMap(Food[] foods, ModelMap modelMap) {
		modelMap.put("foods", foods);
		float calories = NutritionUtility.getTotalCalories(foods);
		float protein = NutritionUtility.getTotalProtein(foods);
		float calcium = NutritionUtility.getTotalCalcium(foods);
		float vitaminD = NutritionUtility.getTotalVitaminD(foods);
		float iron = NutritionUtility.getTotalIron(foods);
		float potassium = NutritionUtility.getTotalPotassium(foods);
		float fiber = NutritionUtility.getTotalFiber(foods);

		modelMap.put("calories", calories);
		modelMap.put("protein", protein);
		modelMap.put("calcium", calcium);
		modelMap.put("vitaminD", vitaminD);
		modelMap.put("iron", iron);
		modelMap.put("totalFat", NutritionUtility.getTotalFat(foods));
		modelMap.put("saturatedFat", NutritionUtility.getTotalSaturatedFat(foods));
		modelMap.put("transFat", NutritionUtility.getTotalTransFat(foods));
		modelMap.put("cholesterol", NutritionUtility.getTotalCholesterol(foods));
		modelMap.put("sodium", NutritionUtility.getTotalSodium(foods));
		modelMap.put("totalCarbohydrate", NutritionUtility.getTotalCarb(foods));
		modelMap.put("fiber", NutritionUtility.getTotalFiber(foods));
		modelMap.put("sugar", NutritionUtility.getTotalSugar(foods));
		modelMap.put("addedSugar", NutritionUtility.getAddedSugar(foods));
		modelMap.put("potassium", NutritionUtility.getTotalPotassium(foods));
		modelMap.put("caffeine", NutritionUtility.getTotalCaffeine(foods));
		modelMap.put("proteinRate", protein);
		modelMap.put("carbRate", NutritionUtility.getCarbInCalories(foods));
		modelMap.put("fatRate", NutritionUtility.getFatInCalories(foods));
		modelMap.put("caloriesDV", NutritionUtility.getCaloriesDaleyValueStatus(calories));
		modelMap.put("proteinDV", NutritionUtility.getProteinDaleyValueStatus(protein));
		modelMap.put("fiberDV", NutritionUtility.getFiberDaleyValueStatus(fiber));
		modelMap.put("potassiumDV", NutritionUtility.getPotassiumDaleyValueStatus(potassium));
		modelMap.put("calciumDV", NutritionUtility.getCalciumDaleyValueStatus(calcium));
		modelMap.put("vitaminDDV", NutritionUtility.getVitaminDDaleyValueStatus(vitaminD));
		modelMap.put("ironDV", NutritionUtility.getIronDaleyValueStatus(iron));
	}

	private void setDashboardDataToModelMap(ModelMap modelMap, Principal principal, LocalDate localDate) {
		List<Food> userFoodsList = foodNutritionService.loadFoodFromDatabaseByUserIdForDate(localDate, appUserDetailsService.loadUserIdByUsername(principal.getName()));
		Food[] userFoodsArray = userFoodsList.toArray(new Food[userFoodsList.size()]);
		List<Memory> userMemories = memoryService.loadMemoryFromDatabaseByUserIdForDate(localDate, appUserDetailsService.loadUserIdByUsername(principal.getName()));
		List<Exercise> userExercises = exerciseService.loadExerciseFromDatabaseByUserIdForDate(localDate, appUserDetailsService.loadUserIdByUsername(principal.getName()));
		modelMap.put("date", localDate);
		modelMap.put("userFoodStatusValid", false);
		modelMap.put("userMemoryStatusValid", false);
		modelMap.put("userExercisesStatusValid", false);

		if (userFoodsList.size() != 0) {
			modelMap.put("userFoodStatusValid", true);
			float calories = NutritionUtility.getTotalCalories(userFoodsArray);
			float protein = NutritionUtility.getTotalProtein(userFoodsArray);
			float potassium = NutritionUtility.getTotalPotassium(userFoodsArray);
			float fiber = NutritionUtility.getTotalFiber(userFoodsArray);
			int caloriesDV = NutritionUtility.getCaloriesDaleyValueStatus(calories);
			int proteinDV = NutritionUtility.getProteinDaleyValueStatus(protein);
			int potassiumDV = NutritionUtility.getPotassiumDaleyValueStatus(potassium);
			int fiberDV = NutritionUtility.getFiberDaleyValueStatus(fiber);

			modelMap.put("foods", userFoodsArray);
			modelMap.put("calories", calories);
			modelMap.put("protein", protein);
			modelMap.put("potassium", calories);
			modelMap.put("fiber", protein);
			modelMap.put("caloriesDV", caloriesDV);
			modelMap.put("proteinDV", proteinDV);
			modelMap.put("potassiumDV", potassiumDV);
			modelMap.put("fiberDV", fiberDV);
		}

		if (userMemories.size() != 0) {
			modelMap.put("userMemoryStatusValid", true);
			String memoriesLoggedForDay = "";
			for (Memory memory : userMemories) {
				memoriesLoggedForDay += memory.getMemory() + "\n";
			}
			modelMap.put("memories", memoriesLoggedForDay);
		}

		if (userExercises.size() != 0) {
			modelMap.put("userExerciseStatusValid", true);
			modelMap.put("exercises", userExercises);
		}
	}


	public void setPrincipalToFood(Food[] foods, Principal principal) {
		User principalUser = appUserDetailsService.getUserByUsername(principal.getName());
		for (Food food : foods) {
			food.setUser(principalUser);
		}
	}

	public void setPrincipalToMemory(Memory memory, Principal principal) {
		User principalUser = appUserDetailsService.getUserByUsername(principal.getName());
		memory.setUser(principalUser);
	}

	public void setPrincipalToExercise(Exercise[] exercises, Principal principal) {
		User principalUser = appUserDetailsService.getUserByUsername(principal.getName());
		for (Exercise exercise : exercises) {
			exercise.setUser(principalUser);
		}
	}

	private void setUserNameToModelMap(ModelMap modelMap, Principal principal) {
		String name = principal.getName();
		modelMap.put("username", name);
	}


}

