package com.ehabaleid.healthdiary.service;

import com.ehabaleid.healthdiary.data.ExerciseRepository;
import com.ehabaleid.healthdiary.model.exercise.Exercise;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExerciseService {

    private static final String API_URL = "https://trackapi.nutritionix.com";
    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise[] fetchExerciseInformation(String userQuery) throws JSONException, JsonProcessingException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("internet.ford.com", 83));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders httpHeaders = FoodNutritionService.setupNutritionAPIHttpHeader();
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("query", userQuery);
        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, httpHeaders);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL + "/v2/natural/exercise", HttpMethod.POST, httpEntity, String.class);
            return new ObjectMapper().readValue(new JSONObject(responseEntity.getBody()).get("exercises").toString(), Exercise[].class);
        } catch (HttpClientErrorException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void saveUserExerciseToDatabase(Exercise[] exercises) {
        exerciseRepository.saveAll(Arrays.asList(exercises));
    }

    public List<Exercise> loadExerciseFromDatabaseByUserId(int userId) throws NoSuchElementException {
        List<Exercise> exercises = exerciseRepository.findByUserId(userId);
        if (exercises == null) {
            throw new NoSuchElementException("No exercises with user id: " + userId);
        }
        return exercises;
    }

    public List<Exercise> loadExerciseFromDatabaseByUserIdForDate(LocalDate localDate, int id) throws NoSuchElementException {
        List<Exercise> exercises = exerciseRepository.findByDateAndUserId(localDate, id);
        if (exercises == null) {
            throw new NoSuchElementException("No exercises found with user id: " + id + "for date: " + localDate);
        }
        return exercises;
    }


}
