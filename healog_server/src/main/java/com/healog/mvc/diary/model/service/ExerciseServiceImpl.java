package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dao.ExerciseDao;
import com.healog.mvc.diary.model.dto.ExerciseDto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseDao exerciseDao;

    public ExerciseServiceImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public List<ExerciseDto> getExercisesByUser(String userId) {
        return exerciseDao.findExercisesByUserId(userId);
    }

    @Override
    public void addExercise(ExerciseDto exercise) {
        exerciseDao.insertExercise(exercise);
    }

    @Override
    public void updateExercise(ExerciseDto exercise) {
        exerciseDao.updateExercise(exercise);
    }

    @Override
    public void deleteExercise(int id) {
        exerciseDao.deleteExercise(id);
    }
}
