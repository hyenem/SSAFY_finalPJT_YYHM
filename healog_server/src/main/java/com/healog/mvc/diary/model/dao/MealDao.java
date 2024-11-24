package com.healog.mvc.diary.model.dao;

import com.healog.mvc.diary.model.dto.MealDto;
import java.util.List;

public interface MealDao {
    // 특정 다이어리의 식단 조회
    List<MealDto> getMealsByDiaryId(int diaryId);

    // MealDto 등록 및 업데이트
    int registerMealImage(MealDto mealDto);

    // 특정 mealType 이미지 삭제
    int deleteMealImage(int mealId, String mealType);

    // Meal 생성 (다이어리 ID 기준)
    void insertMeal(int diaryId);

    // Meal 조회 (다이어리 ID 기준)
    MealDto getMealByDiaryId(int diaryId);
}
