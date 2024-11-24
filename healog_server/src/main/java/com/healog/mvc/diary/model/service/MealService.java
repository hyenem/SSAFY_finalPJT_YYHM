package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dto.MealDto;
import java.util.List;

public interface MealService {
    // 특정 다이어리의 식단 조회
    List<MealDto> getMealsByDiaryId(int diaryId);

    // Meal 이미지 등록 및 업데이트
    boolean registerMealImage(MealDto mealDto);

    // Meal 이미지 삭제
    boolean deleteMealImage(int mealId, String mealType);

    // Meal이 없으면 생성 후 ID 반환
    int ensureMealExists(int diaryId);
}
