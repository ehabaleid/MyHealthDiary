package com.ehabaleid.healthdiary.service;

import com.ehabaleid.healthdiary.data.FoodRepository;
import com.ehabaleid.healthdiary.model.nutrition.Food;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.net.Proxy;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class FoodNutritionService {

    @Autowired
    FoodRepository foodRepository;
    private static final String API_URL = "https://trackapi.nutritionix.com";


    public Food[] fetchNutritionInformation(String userQuery) throws JSONException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = setupNutritionAPIHttpHeader();
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("query", userQuery);
        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, httpHeaders);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL + "/v2/natural/nutrients", HttpMethod.POST, httpEntity, String.class);
            return new ObjectMapper().readValue(new JSONObject(responseEntity.getBody()).get("foods").toString(), Food[].class);
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static HttpHeaders setupNutritionAPIHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-app-id", System.getenv("NUTRITION_API_ID"));
        httpHeaders.add("x-app-key", System.getenv("NUTRITION_API_KEY"));
        httpHeaders.add("x-remote-user-id", "0");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return httpHeaders;
    }

    public void saveUserFoodsToDatabase(Food[] foods) {
        foodRepository.saveAll(Arrays.asList(foods));
    }

    public List<Food> loadFoodFromDatabaseByUserId(int id) throws NoSuchElementException {

        List<Food> foods = foodRepository.findByUserId(id);
        if (foods == null) {
            throw new NoSuchElementException("No food found with user id: " + id);
        }
        return foods;
    }

    public List<Food> loadFoodFromDatabaseByUserIdForDate(LocalDate localDate, int id) throws NoSuchElementException {
        List<Food> foods = foodRepository.findByDateAndUserId(localDate, id);
        if (foods == null) {
            throw new NoSuchElementException("No food found with user id: " + id + "for date: " + localDate);
        }
        return foods;
    }

    public Map<LocalDate, List<Food>> groupUserFoodsByDate(List<Food> foods) {
        return foods.stream().sorted(Comparator.comparing(Food::getDate)).collect(Collectors.groupingBy(Food::getDate));
    }



}
