package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dao.CategoryDao;
import com.healog.mvc.diary.model.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<String> getDistinctExerciseAreas() {
        return categoryDao.getDistinctExerciseAreas();
    }

    @Override
    public List<CategoryDto> getExercisesByArea(String area) {
        return categoryDao.getExercisesByArea(area);
    }
}
