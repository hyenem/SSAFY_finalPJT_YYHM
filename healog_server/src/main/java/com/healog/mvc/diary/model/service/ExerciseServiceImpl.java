package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dao.ExerciseDao;
import com.healog.mvc.diary.model.dto.ExerciseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseDao exerciseDao;

    @Override
    public List<ExerciseDto> getExercisesByDiaryId(int diaryId) {
        return exerciseDao.getExercisesByDiaryId(diaryId);
    }

    @Override
    public ExerciseDto getExerciseById(int id) {
        return exerciseDao.getExerciseById(id);
    }

    @Override
    public void registerExercise(ExerciseDto exerciseDto) {
        exerciseDao.insertExercise(exerciseDto);
    }

    @Override
    public void updateExercise(ExerciseDto exerciseDto) {
        exerciseDao.updateExercise(exerciseDto);
    }

    @Override
    public void deleteExercise(int id) {
        exerciseDao.deleteExercise(id);
    }

    @Override
    public void markExerciseAsDone(int id, String postureImg, int done) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("postureImg", postureImg);
        params.put("done", done);
        exerciseDao.markExerciseAsDone(params);
    }

    

}
