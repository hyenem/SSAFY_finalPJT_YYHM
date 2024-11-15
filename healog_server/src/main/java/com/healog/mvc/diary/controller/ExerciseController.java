package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.ExerciseDto;
import com.healog.mvc.diary.model.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    // 특정 다이어리의 운동 목록 조회
    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getExercisesByDiaryId(@RequestParam int diaryId) {
        List<ExerciseDto> exercises = exerciseService.getExercisesByDiaryId(diaryId);
        return ResponseEntity.ok(exercises);
    }

    // 특정 운동 계획 조회
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable int id) {
        ExerciseDto exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    // 운동 계획 등록
    @PostMapping
    public ResponseEntity<String> registerExercise(@RequestBody ExerciseDto exerciseDto) {
        exerciseService.registerExercise(exerciseDto);
        return ResponseEntity.ok("Exercise registered successfully.");
    }

    // 운동 계획 수정
    @PutMapping
    public ResponseEntity<String> updateExercise(@RequestBody ExerciseDto exerciseDto) {
        exerciseService.updateExercise(exerciseDto);
        return ResponseEntity.ok("Exercise updated successfully.");
    }

    // 운동 계획 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable int id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok("Exercise deleted successfully.");
    }

    // 운동 완료 여부 업데이트
    @PostMapping("/done")
    public ResponseEntity<String> markExerciseAsDone(@RequestParam(required = true) Integer id, @RequestParam String postureImg) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Invalid ID provided.");
        }
        exerciseService.markExerciseAsDone(id, postureImg);
        return ResponseEntity.ok("Exercise marked as done.");
    }

}
