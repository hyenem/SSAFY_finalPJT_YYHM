package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dao.MealDao;
import com.healog.mvc.diary.model.dto.MealDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealDao mealDao;

    @Override
    public List<MealDto> getMealsByDiaryId(int diaryId) {
        return mealDao.getMealsByDiaryId(diaryId);
    }

    @Override
    public void saveMeal(MealDto mealDto) {
        mealDao.insertMeal(mealDto);
    }

    @Override
    public void deleteMealById(int id) {
        mealDao.deleteMealById(id);
    }
}
