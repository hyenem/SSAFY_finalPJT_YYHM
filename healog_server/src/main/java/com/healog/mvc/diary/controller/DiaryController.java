package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.DiaryDto;
import com.healog.mvc.diary.model.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public ResponseEntity<List<DiaryDto>> getDiaryList(@RequestParam String userId) {
        try {
            List<DiaryDto> diaryList = diaryService.getDiaryList(userId);
            return ResponseEntity.ok(diaryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/condition")
    public ResponseEntity<DiaryDto> getDiaryByDate(
            @RequestParam String userId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day) {
        try {
            DiaryDto diary = diaryService.getDiaryByDate(userId, year, month, day);
            System.out.println(diary);
            if (diary == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(diary);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/condition")
    public ResponseEntity<String> saveDiary(@RequestBody DiaryDto diaryDto) {
        try {
            diaryService.saveDiary(diaryDto);
            return ResponseEntity.ok("Diary saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save diary.");
        }
    }
}

