package com.ehabaleid.healthdiary.service;

import com.ehabaleid.healthdiary.model.exercise.Exercise;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseServiceTest {

    private static ExerciseService exerciseService;

    @BeforeAll
    public static void setup() {
        exerciseService = new ExerciseService();

    }

    @Test
    void return_response_given_user_query() throws JSONException, JsonProcessingException {
        List<Exercise> exercises = Arrays.asList(exerciseService.fetchExerciseInformation("run 30 min"));
        assertEquals("running", exercises.get(0).getExerciseName());
        assertNotNull(exercises);
    }


}