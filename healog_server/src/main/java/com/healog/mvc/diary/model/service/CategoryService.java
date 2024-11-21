package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<String> getDistinctExerciseAreas();
    List<CategoryDto> getExercisesByArea(String area);
}
