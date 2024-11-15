package com.healog.mvc.diary.model.dao;

import java.util.List;
import java.util.Map;

import com.healog.mvc.diary.model.dto.ExerciseDto;

public interface ExerciseDao {
	// 특정 다이어리의 운동 목록 조회
    List<ExerciseDto> getExercisesByDiaryId(int diaryId);

    // 특정 운동 계획 조회
    ExerciseDto getExerciseById(int id);

    // 운동 계획 등록
    int insertExercise(ExerciseDto exerciseDto);

    // 운동 계획 수정
    int updateExercise(ExerciseDto exerciseDto);

    // 운동 계획 삭제
    int deleteExercise(int id);

    // 운동 완료 여부 업데이트
    int markExerciseAsDone(Map<String, Object> params);
}
