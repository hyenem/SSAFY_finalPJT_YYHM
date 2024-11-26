package com.healog.mvc.diary.model.service;

import java.util.List;

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

	@Override
	public List<Pt> getRequestByTrainer(String trainerId) {
		return ptDao.selectRequestPtByTrainer(trainerId);
	}

	@Override
	public void acceptRequest(int diaryId) {
		ptDao.updateRequestState(diaryId, 3);
	}

	@Override
	public void denyRequest(int diaryId) {
		ptDao.updateRequestState(diaryId, 6);
		
	}

	@Override
	public List<Pt> getRequestByUser(String userId) {
		return ptDao.selectRequestPtByUserId(userId);
	}

	@Override
	public void checkResponse(int diaryId) {
		Pt pt = ptDao.selectPtByDiaryId(diaryId);
		int state = pt.getRequestState();
		if(state==6|| state==8 || state==10) {
			ptDao.updateRequestStateDirect(diaryId, 1);
		} else if(state==3 || state==4 || state==7 || state==9) {
			ptDao.updateRequestStateDirect(diaryId, 0);
		} else if (state == 5) {
			String trainerId = trainerDao.selectTrainerByDiary(diaryId);
			ptDao.insertOrUpdatePt(trainerId, diaryId, pt.getRequestTime(), 0, 1, 0);
		}
	}

	@Override
	public void requestUpdate(int diaryId, int time) {
		Pt pt = ptDao.selectPtByDiaryId(diaryId);
		String trainerId = trainerDao.selectTrainerByDiary(diaryId);
		ptDao.insertOrUpdatePt(trainerId, diaryId, pt.getTime(), 0, 2, time);
	}

	@Override
	public void requestDelete(int diaryId) {
		ptDao.updateRequestStateDirect(diaryId, 4);
	}

	@Override
	public List<Pt> getPtByUser(String userId) {
		return ptDao.selectPtByUserId(userId);
	}
}
