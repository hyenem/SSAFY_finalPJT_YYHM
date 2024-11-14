package com.healog.mvc.diary.model.service;

import java.util.List;

import com.healog.mvc.diary.model.dto.ExerciseDto;

public interface ExerciseService {
    List<ExerciseDto> getExercisesByUser(String userId); // 특정 사용자의 운동 조회
    void addExercise(ExerciseDto exercise);             // 운동 추가
    void updateExercise(ExerciseDto exercise);          // 운동 수정
    void deleteExercise(int id);                        // 운동 삭제
}
