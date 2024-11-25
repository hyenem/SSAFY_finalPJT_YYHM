package com.healog.mvc.diary.model.service;

import org.springframework.stereotype.Service;

import com.healog.mvc.account.model.dao.TrainerDao;
import com.healog.mvc.diary.model.dao.DiaryDao;
import com.healog.mvc.diary.model.dao.PtDao;
import com.healog.mvc.diary.model.dto.DiaryDto;
import com.healog.mvc.diary.model.dto.Pt;

@Service
public class PtServiceImpl implements PtService {
	
	private PtDao ptDao;
	private DiaryDao diaryDao;
	private TrainerDao trainerDao;
	public PtServiceImpl(PtDao ptDao, DiaryDao diaryDao, TrainerDao trainerDao) {
		this.ptDao = ptDao;
		this.diaryDao = diaryDao;
		this.trainerDao = trainerDao;
	}
	
	@Override
	public int getPt(String userId, int year, int month, int day) {
		DiaryDto diary = diaryDao.getDiaryByDate(userId, year, month, day);
		int result = -1;
		try {
			result = (diary==null ? 0 : ptDao.selectPt(diary.getId()));
			return result;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void setPt(String trainerId, int diaryId, int time) {
		ptDao.insertPt(trainerId, diaryId, time);
	}

	@Override
	public Pt getPtInfo(String userId, int year, int month, int day) {
		DiaryDto diary = diaryDao.getDiaryByDate(userId, year, month, day);
		return ptDao.selectPtByDiaryId(diary.getId());
	}

	@Override
	public void updateTime(int id, int time) {
		ptDao.updateTime(id, time);
	}

	@Override
	public void deletePt(int id) {
		ptDao.deletePt(id);
		
	}

	@Override
	public void requestInsert(int id, int time, int requestStatus) {
		String trainerId = trainerDao.selectTrainerByDiary(id);
		try {
			ptDao.insertOrUpdatePt(trainerId, id, time, 0 , requestStatus, 0);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
