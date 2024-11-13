package com.healog.mvc.exercise.model.dao;

import com.healog.mvc.exercise.model.dto.ExerciseDto;
import java.util.List;

public interface ExerciseDao {
    List<ExerciseDto> findExercisesByUserId(String userId);
    void insertExercise(ExerciseDto exercise);
    void updateExercise(ExerciseDto exercise);
    void deleteExercise(int id);
}
