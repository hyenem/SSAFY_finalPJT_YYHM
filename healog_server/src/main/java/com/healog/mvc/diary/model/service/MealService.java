package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dto.MealDto;

public interface MealService {

    // 특정 다이어리의 식단 조회
    MealDto getMealsByDiaryId(int diaryId);

    // 식단 등록
    void saveMeal(MealDto mealDto);

    // 식단 삭제
    void deleteMealById(int id);
}