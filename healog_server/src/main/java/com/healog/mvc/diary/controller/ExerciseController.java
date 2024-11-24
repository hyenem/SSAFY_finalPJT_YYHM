package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.ExerciseDto;
import com.healog.mvc.diary.model.service.ExerciseService;
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
@RequestMapping("/user/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ResourceLoader resourceLoader;
    
    // 파일 저장 함수
    private static final String IMG_DIRECTORY = "src/main/resources/static/img/";
    private static final String IMG_URL_PREFIX = "/img/";
    
    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getExercisesByDiaryId(@RequestParam int diaryId) {
        List<ExerciseDto> exercises = exerciseService.getExercisesByDiaryId(diaryId);
        return ResponseEntity.ok(exercises);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable int id) {
        ExerciseDto exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    public ResponseEntity<String> registerExercise(@RequestBody ExerciseDto exerciseDto) {
        exerciseService.registerExercise(exerciseDto);
        return ResponseEntity.ok("Exercise registered successfully.");
    }

    @PutMapping
    public ResponseEntity<String> updateExercise(
            @RequestPart("exerciseDto") ExerciseDto exerciseDto,
            @RequestPart(value = "postureImg", required = false) MultipartFile postureImg) {

        String savedFilePath = null;

        try {
            // 파일 저장
            if (postureImg != null && !postureImg.isEmpty()) {
                savedFilePath = saveFile(postureImg);
                exerciseDto.setPostureImg(savedFilePath);
            }

            // 운동 데이터 업데이트
            exerciseService.updateExercise(exerciseDto);
            return ResponseEntity.ok("Exercise updated successfully.");
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing file upload: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable int id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok("Exercise deleted successfully.");
    }

    @PostMapping("/done")
    public ResponseEntity<String> markExerciseAsDone(
        @RequestParam int id,
        @RequestParam(required = false) MultipartFile postureImg) {
    	
    	String savedFilePath = null;
    	
    	try {
    		if (postureImg != null && !postureImg.isEmpty()) {
    			savedFilePath = saveFile(postureImg);
    		}

    		exerciseService.markExerciseAsDone(id, savedFilePath);
    		return ResponseEntity.ok("Exercise marked as done.");
    	} catch (IOException e) {
    		 return new ResponseEntity<>("Error processing file upload: " + e.getMessage(), 
                     HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

    private String saveFile(MultipartFile file) throws IOException {
        // 유니크한 파일 이름 생성
        String uuidImgName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        
        // 파일 저장 경로
        Path filePath = Paths.get(IMG_DIRECTORY, uuidImgName);
        
        // 파일 저장
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        // URL로 반환할 경로
        return IMG_URL_PREFIX + uuidImgName;
    }
}
