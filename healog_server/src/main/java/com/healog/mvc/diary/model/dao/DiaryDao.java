package com.healog.mvc.diary.model.dao;

import com.healog.mvc.diary.model.dto.DiaryDto;
import java.util.List;

public interface DiaryDao {
    List<DiaryDto> getDiaryListByUser(String userId);      // 사용자 diary 조회
    List<DiaryDto> getDiaryListByTrainer(String treatingUserId); // 트레이너의 특정 사용자 diary 조회
    void updateDiaryCondition(int diaryId, String condition);    // diary condition 수정
}
