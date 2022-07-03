package com.ehabaleid.healthdiary.data;

import com.ehabaleid.healthdiary.model.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    List<Exercise> findByUserId(int id);
    List<Exercise> findByDateAndUserId(LocalDate date, int id);
}
