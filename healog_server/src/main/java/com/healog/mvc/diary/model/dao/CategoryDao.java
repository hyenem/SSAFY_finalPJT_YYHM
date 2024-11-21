package com.healog.mvc.diary.model.dao;

import com.healog.mvc.diary.model.dto.CategoryDto;

import java.util.List;

public interface CategoryDao {
    List<String> getDistinctExerciseAreas();
    List<CategoryDto> getExercisesByArea(String area);
}
