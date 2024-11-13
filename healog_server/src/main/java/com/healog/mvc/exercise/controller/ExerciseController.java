package com.healog.mvc.exercise.controller;

import com.healog.mvc.exercise.model.dto.ExerciseDto;
import com.healog.mvc.exercise.model.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/user/{userId}")
    public List<ExerciseDto> getExercisesByUser(@PathVariable String userId) {
        return exerciseService.getExercisesByUser(userId);
    }

    @PostMapping
    public void addExercise(@RequestBody ExerciseDto exercise) {
        exerciseService.addExercise(exercise);
    }

    @PutMapping
    public void updateExercise(@RequestBody ExerciseDto exercise) {
        exerciseService.updateExercise(exercise);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable int id) {
        exerciseService.deleteExercise(id);
    }
}
