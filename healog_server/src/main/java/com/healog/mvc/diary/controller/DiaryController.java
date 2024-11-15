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

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public ResponseEntity<List<DiaryDto>> getDiaryList(@RequestParam String userId) {
        List<DiaryDto> diaryList = diaryService.getDiaryList(userId);
        return new ResponseEntity<>(diaryList, HttpStatus.OK);
    }

    @GetMapping("/condition")
    public ResponseEntity<DiaryDto> getDiaryByDate(
            @RequestParam String userId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("year", year);
        params.put("month", month);
        params.put("day", day);
        
        DiaryDto diary = diaryService.getDiaryByDate(params);
        if (diary == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(diary, HttpStatus.OK);
    }

    @PutMapping("/condition")
    public ResponseEntity<String> saveDiary(@RequestBody DiaryDto diaryDto) {
        try {
            diaryService.saveDiary(diaryDto);
            return new ResponseEntity<>("Diary saved successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save diary.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
