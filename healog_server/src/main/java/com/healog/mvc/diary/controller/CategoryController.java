package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.CategoryDto;
import com.healog.mvc.diary.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 운동 부위 목록 조회
    @GetMapping("/areas")
    public ResponseEntity<List<String>> getExerciseAreas() {
        List<String> exerciseAreas = categoryService.getDistinctExerciseAreas();
        return new ResponseEntity<>(exerciseAreas, HttpStatus.OK);
    }

    // 특정 부위에 해당하는 운동 목록 조회
    @GetMapping("/exercises")
    public ResponseEntity<List<CategoryDto>> getExercisesByArea(@RequestParam String area) {
        List<CategoryDto> exercises = categoryService.getExercisesByArea(area);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }
}
