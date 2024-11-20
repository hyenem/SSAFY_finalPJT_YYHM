package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.MealDto;
import com.healog.mvc.diary.model.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user/meal")
public class MealController {
    
    private final MealService mealService;

    @Autowired    
    private ResourceLoader resourceLoader;
    
    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    // 식단 등록
    @PostMapping
    public ResponseEntity<String> saveMeal(
            @RequestParam int diaryId,
            @RequestParam(required = false) MultipartFile breakfastImg,
            @RequestParam(required = false) MultipartFile lunchImg,
            @RequestParam(required = false) MultipartFile dinnerImg) {
        
        String savedBreakfastPath = null;
        String savedLunchPath = null;
        String savedDinnerPath = null;

        try {
            // 파일 업로드 처리
            if (breakfastImg != null && !breakfastImg.isEmpty()) {
                savedBreakfastPath = saveFile(breakfastImg);
            }
            if (lunchImg != null && !lunchImg.isEmpty()) {
                savedLunchPath = saveFile(lunchImg);
            }
            if (dinnerImg != null && !dinnerImg.isEmpty()) {
                savedDinnerPath = saveFile(dinnerImg);
            }
            
            MealDto mealDto = new MealDto();
            mealDto.setDiaryId(diaryId);
            mealDto.setBreakfastImg(savedBreakfastPath);
            mealDto.setLunchImg(savedLunchPath);
            mealDto.setDinnerImg(savedDinnerPath);

            mealService.saveMeal(mealDto);
            return new ResponseEntity<>("Meal saved successfully.", HttpStatus.CREATED);

        } catch (IOException e) {
            return new ResponseEntity<>("Error processing file upload: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 식단 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMealById(@PathVariable int id) {
        try {
            mealService.deleteMealById(id);
            return new ResponseEntity<>("Meal deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete meal: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 파일 저장 함수
    private String saveFile(MultipartFile file) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/img");
        File directory = resource.getFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File savedFile = new File(directory, fileName);
        file.transferTo(savedFile);

        return "/static/img/" + fileName;
    }
}