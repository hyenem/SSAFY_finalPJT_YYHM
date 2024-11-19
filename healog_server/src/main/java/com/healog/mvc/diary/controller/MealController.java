// MealController.java
package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.MealDto;
import com.healog.mvc.diary.model.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user/meal")
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping
    public ResponseEntity<String> saveMeal(@RequestParam int diaryId,
                                           @RequestParam(required = false) MultipartFile breakfastImg,
                                           @RequestParam(required = false) MultipartFile lunchImg,
                                           @RequestParam(required = false) MultipartFile dinerImg) {
        MealDto mealDto = new MealDto();
        mealDto.setDiaryId(diaryId);

        try {
            // 파일 업로드 처리
            if (breakfastImg != null && !breakfastImg.isEmpty()) {
                mealDto.setBreakfastImg(saveFile(breakfastImg));
            }
            if (lunchImg != null && !lunchImg.isEmpty()) {
                mealDto.setLunchImg(saveFile(lunchImg));
            }
            if (dinerImg != null && !dinerImg.isEmpty()) {
                mealDto.setDinnerImg(saveFile(dinerImg));
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing file upload: " + e.getMessage());
        }

        // Meal 저장
        mealService.saveMeal(mealDto);
        return ResponseEntity.ok("Meal saved successfully.");
    }

    private String saveFile(MultipartFile file) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/img");
        File directory = resource.getFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 이름 설정 및 저장
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File savedFile = new File(directory, fileName);
        file.transferTo(savedFile);

        return "/static/img/" + fileName; // 저장된 파일 경로 반환
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable int id) {
        mealService.deleteMealById(id);
        return ResponseEntity.ok("Meal deleted successfully.");
    }
}
