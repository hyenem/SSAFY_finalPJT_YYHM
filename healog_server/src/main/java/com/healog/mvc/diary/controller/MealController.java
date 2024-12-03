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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/meal")
public class MealController {
    
    private final MealService mealService;

    @Autowired    
    private ResourceLoader resourceLoader;

    private static final String IMG_DIRECTORY = "src/main/resources/static/img/";
    private static final String IMG_URL_PREFIX = "/img/";

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    // 특정 다이어리의 식단 조회
    @GetMapping
    public ResponseEntity<List<MealDto>> getMealsByDiaryId(@RequestParam int diaryId) {
        List<MealDto> meals = mealService.getMealsByDiaryId(diaryId);
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    // 식단 이미지 등록 (아침, 점심, 저녁)
    @PostMapping
    public ResponseEntity<String> registerMealImage(
        @RequestParam int diaryId,
        @RequestParam String mealType,
        @RequestPart(value = "file", required = false) MultipartFile file) {

        System.out.println("Diary ID: " + diaryId);
        System.out.println("Meal Type: " + mealType);

        try {
            // Meal 레코드가 없으면 생성
            int mealId = mealService.ensureMealExists(diaryId);

            // 파일 저장
            String savedFilePath = null;
            if (file != null && !file.isEmpty()) {
                savedFilePath = saveFile(file);
            }

            // MealDto 생성 및 업데이트
            MealDto mealDto = new MealDto();
            mealDto.setId(mealId);
            if ("breakfast".equals(mealType)) {
                mealDto.setBreakfastImg(savedFilePath);
            } else if ("lunch".equals(mealType)) {
                mealDto.setLunchImg(savedFilePath);
            } else if ("dinner".equals(mealType)) {
                mealDto.setDinnerImg(savedFilePath);
            }

            boolean isRegistered = mealService.registerMealImage(mealDto);
            if (isRegistered) {
                return new ResponseEntity<>("Meal image registered successfully!", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Failed to register meal image.", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error saving file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 식단 이미지 삭제 (아침, 점심, 저녁)
    @DeleteMapping("/{mealId}")
    public ResponseEntity<String> deleteMealImage(
        @PathVariable int mealId,
        @RequestParam String mealType) {

        boolean isDeleted = mealService.deleteMealImage(mealId, mealType);
        if (isDeleted) {
            return new ResponseEntity<>("Meal image deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete meal image.", HttpStatus.BAD_REQUEST);
    }

    private String saveFile(MultipartFile file) throws IOException {
        // 유니크한 파일 이름 생성
        String uuidImgName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        
        // 파일 저장 경로
        Path filePath = Paths.get(IMG_DIRECTORY, uuidImgName);
        
        // 디렉토리 생성
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // 파일 저장
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        // URL로 반환할 경로
        return IMG_URL_PREFIX + uuidImgName;
    }
}
