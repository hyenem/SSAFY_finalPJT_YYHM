package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dao.MealDao;
import com.healog.mvc.diary.model.dto.MealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealDao mealDao;

    @Override
    public List<MealDto> getMealsByDiaryId(int diaryId) {
    	System.out.println(mealDao.getMealsByDiaryId(diaryId));
    	System.out.println("*******");
        return null;
    }

    @Override
    public boolean registerMealImage(MealDto mealDto) {
        return mealDao.registerMealImage(mealDto) > 0;
    }

    @Override
    public boolean deleteMealImage(int mealId, String mealType) {
        return mealDao.deleteMealImage(mealId, mealType) > 0;
    }

    @Override
    public int ensureMealExists(int diaryId) {
        MealDto existingMeal = mealDao.getMealByDiaryId(diaryId);
        if (existingMeal != null) {
            return existingMeal.getId();
        }

        // 새 Meal 생성
        mealDao.insertMeal(diaryId);
        return mealDao.getMealByDiaryId(diaryId).getId();
    }
}
