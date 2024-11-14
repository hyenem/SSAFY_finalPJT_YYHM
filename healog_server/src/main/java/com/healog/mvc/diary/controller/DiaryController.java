package com.healog.mvc.diary.controller;

import com.healog.mvc.diary.model.dto.DiaryDto;
import com.healog.mvc.diary.model.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {
	
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

}
