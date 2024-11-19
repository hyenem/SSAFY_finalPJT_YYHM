package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.MealDto;
import com.healog.mvc.diary.model.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/user/meal")
public class MealController {
    
    private static final Logger logger = LoggerFactory.getLogger(MealController.class);
    
    private final MealService mealService;
    
    @Value("${app.meal-images.upload-dir:C:/meal-images/}")
    private String fileDirectory;

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
        
        try {
            validateDiaryId(diaryId);
            validateImages(breakfastImg, lunchImg, dinnerImg);

            MealDto mealDto = new MealDto();
            mealDto.setDiaryId(diaryId);

            // 이미지 처리
            if (breakfastImg != null && !breakfastImg.isEmpty()) {
                mealDto.setBreakfastImg(saveFileToLocal(breakfastImg));
            }
            if (lunchImg != null && !lunchImg.isEmpty()) {
                mealDto.setLunchImg(saveFileToLocal(lunchImg));
            }
            if (dinnerImg != null && !dinnerImg.isEmpty()) {
                mealDto.setDinnerImg(saveFileToLocal(dinnerImg));
            }

            mealService.saveMeal(mealDto);
            return new ResponseEntity<>("Meal saved successfully.", HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            logger.error("Invalid input data: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.error("File processing error: {}", e.getMessage());
            return new ResponseEntity<>("Error processing file upload: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Unexpected error while saving meal: {}", e.getMessage());
            return new ResponseEntity<>("An unexpected error occurred", 
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
            logger.error("Error deleting meal: {}", e.getMessage());
            return new ResponseEntity<>("Failed to delete meal: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 파일 저장 함수
    private String saveFileToLocal(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // 파일 이름 생성
        String fileName = System.currentTimeMillis() + "_" + 
                         (file.getOriginalFilename() != null ? file.getOriginalFilename() : "unknown");
        Path uploadPath = Paths.get(fileDirectory);
        
        // 디렉토리 생성
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 파일 저장
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }

    // 입력값 검증
    private void validateDiaryId(int diaryId) {
        if (diaryId <= 0) {
            throw new IllegalArgumentException("Invalid diary ID");
        }
    }

    private void validateImages(MultipartFile... images) {
        for (MultipartFile image : images) {
            if (image != null && !image.isEmpty()) {
                validateImageFile(image);
            }
        }
    }

    private void validateImageFile(MultipartFile file) {
        // 파일 크기 검증 (예: 10MB)
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("File size exceeds maximum limit (10MB)");
        }

        // 파일 타입 검증
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }
    }
}