package com.healog.mvc.diary.model.dao;

import java.util.List;

import com.healog.mvc.diary.model.dto.ExerciseDto;

public interface ExerciseDao {
    List<ExerciseDto> findExercisesByUserId(String userId);
    void insertExercise(ExerciseDto exercise);
    void updateExercise(ExerciseDto exercise);
    void deleteExercise(int id);
}
