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
    public ResponseEntity<String> updateExercise(@RequestBody ExerciseDto exerciseDto) {
        System.out.println("Updating Exercise DTO: " + exerciseDto);

        exerciseService.updateExercise(exerciseDto);
        return ResponseEntity.ok("Exercise updated successfully.");
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

        String savedFilePath = saveFileIfNotNull(postureImg);
        exerciseService.markExerciseAsDone(id, savedFilePath);
        return ResponseEntity.ok("Exercise marked as done.");
    }

    private String saveFileIfNotNull(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            Resource resource = resourceLoader.getResource("classpath:/static/img");
            File directory = resource.getFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File savedFile = new File(directory, fileName);
            file.transferTo(savedFile);

            return "/img/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }
}
