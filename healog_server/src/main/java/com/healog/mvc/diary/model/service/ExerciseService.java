package com.healog.mvc.diary.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.healog.mvc.diary.model.dto.ExerciseDto;

public interface ExerciseService {
	// 특정 다이어리의 운동 목록 조회
    List<ExerciseDto> getExercisesByDiaryId(int diaryId);

    // 특정 운동 계획 조회
    ExerciseDto getExerciseById(int id);

    // 운동 계획 등록
    void registerExercise(ExerciseDto exerciseDto);

    // 운동 계획 수정
    void updateExercise(ExerciseDto exerciseDto);

    // 운동 계획 삭제
    void deleteExercise(int id);

    // 운동 완료 여부 업데이트
    void markExerciseAsDone(int id, String postureImg);
    

}
