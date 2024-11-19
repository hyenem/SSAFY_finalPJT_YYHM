package com.healog.mvc.diary.model.dao;

import com.healog.mvc.diary.model.dto.DiaryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DiaryDao {

	// 특정 회원의 한 달치 다이어리 조회
    List<DiaryDto> getDiaryListByUserId(String userId);

    // 특정 날짜의 다이어리 조회
    DiaryDto getDiaryByDate(Map<String, Object> params);

    // 다이어리 업데이트
    int updateDiary(DiaryDto diaryDto);
}
