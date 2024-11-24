package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dao.DiaryDao;
import com.healog.mvc.diary.model.dto.DiaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryDao diaryDao;

    @Override
    public List<DiaryDto> getDiaryList(String userId) {
        return diaryDao.getDiaryListByUserId(userId);
    }

    @Override
    public DiaryDto getDiaryByDate(String userId, int year, int month, int day) {
    	DiaryDto diary = diaryDao.getDiaryByDate(userId, year, month, day);
    	if(diary == null) {
    		diary = new DiaryDto();
    		diary.setUserId(userId);
    		diary.setYear(year);
    		diary.setMonth(month);
    		diary.setDay(day);
    		diary.setCondition(null);
    		diaryDao.insertOrUpdateDiary(diary);
    	}
        return diaryDao.getDiaryByDate(userId, year, month, day);
    }

    @Override
    public void saveDiary(DiaryDto diaryDto) {
        diaryDao.insertOrUpdateDiary(diaryDto);
    }
}

