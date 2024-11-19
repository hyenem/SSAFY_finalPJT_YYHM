package com.healog.mvc.diary.model.dao;

import com.healog.mvc.diary.model.dto.MealDto;
import java.util.List;

public interface MealDao {

    // 특정 다이어리의 식단 조회
    MealDto getMealsByDiaryId(int diaryId);

    // 식단 등록
    int insertMeal(MealDto mealDto);

    // 식단 삭제
    int deleteMealById(int id);
}