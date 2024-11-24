package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dto.DiaryDto;
import java.util.List;
import java.util.Map;

public interface DiaryService {

    // 특정 회원의 한 달치 다이어리 조회
    List<DiaryDto> getDiaryList(String userId);

    // 특정 날짜의 다이어리 조회
    DiaryDto getDiaryByDate(String userId, int year, int month, int day);

    // 다이어리 생성 또는 업데이트
    void saveDiary(DiaryDto diaryDto);

}
