package com.healog.mvc.diary.model.service;

import com.healog.mvc.diary.model.dao.DiaryDao;
import com.healog.mvc.diary.model.dto.DiaryDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryDao diaryDao;

    public DiaryServiceImpl(DiaryDao diaryDao) {
        this.diaryDao = diaryDao;
    }

    @Override
    public List<DiaryDto> getDiaryListByUser(String userId) {
        return diaryDao.getDiaryListByUser(userId);
    }

    @Override
    public List<DiaryDto> getDiaryListByTrainer(String treatingUserId) {
        return diaryDao.getDiaryListByTrainer(treatingUserId);
    }

    @Override
    public void updateDiaryCondition(int diaryId, String condition) {
        diaryDao.updateDiaryCondition(diaryId, condition);
    }
}
