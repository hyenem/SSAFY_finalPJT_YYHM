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
import java.util.List;

@RestController
@RequestMapping("/user/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ResourceLoader resourceLoader;

    // 특정 다이어리의 운동 목록 조회
    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getExercisesByDiaryId(@RequestParam int diaryId) {
        List<ExerciseDto> exercises = exerciseService.getExercisesByDiaryId(diaryId);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    // 특정 운동 계획 조회
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable int id) {
        ExerciseDto exercise = exerciseService.getExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    // 운동 계획 등록
    @PostMapping
    public ResponseEntity<String> registerExercise(@RequestBody ExerciseDto exerciseDto) {
        exerciseService.registerExercise(exerciseDto);
        return new ResponseEntity<>("Exercise registered successfully.", HttpStatus.OK);
    }

    // 운동 계획 수정
    @PutMapping
    public ResponseEntity<String> updateExercise(@RequestBody ExerciseDto exerciseDto) {
        exerciseService.updateExercise(exerciseDto);
        return new ResponseEntity<>("Exercise updated successfully.", HttpStatus.OK);
    }

    // 운동 계획 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable int id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>("Exercise deleted successfully.", HttpStatus.OK);
    }

    // 운동 완료 여부 업데이트
    @PostMapping("/done")
    public ResponseEntity<String> markExerciseAsDone(@RequestParam int id,
            @RequestParam(required = false) MultipartFile postureImg) {
        String savedFilePath = null;

        try {
            // 파일 업로드 처리
            if (postureImg != null && !postureImg.isEmpty()) {
                savedFilePath = saveFile(postureImg);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing file upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 운동 완료 여부 업데이트
        exerciseService.markExerciseAsDone(id, savedFilePath);
        return new ResponseEntity<>("Exercise marked as done.", HttpStatus.OK);
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

        return "/img/" + fileName; // 저장된 파일 경로 반환
    }
}