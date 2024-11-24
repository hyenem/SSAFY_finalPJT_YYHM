package com.healog.mvc.diary.model.service;

import org.springframework.stereotype.Service;

import com.healog.mvc.diary.model.dao.DiaryDao;
import com.healog.mvc.diary.model.dao.PtDao;
import com.healog.mvc.diary.model.dto.DiaryDto;

@Service
public class PtServiceImpl implements PtService {
	
	private PtDao ptDao;
	private DiaryDao diaryDao;
	public PtServiceImpl(PtDao ptDao, DiaryDao diaryDao) {
		this.ptDao = ptDao;
		this.diaryDao = diaryDao;
	}
	
	@Override
	public boolean getPt(String userId, int year, int month, int day) {
		DiaryDto diary = diaryDao.getDiaryByDate(userId, year, month, day);
		int result = (diary==null ? 0 : ptDao.selectPt(diary.getId()));
		System.out.println(result);
		return result!=0;
	}

	@Override
	public void setPt(String trainerId, int diaryId, int time) {
		ptDao.insertPt(trainerId, diaryId, time);
	}
	
}
