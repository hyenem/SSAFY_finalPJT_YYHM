package com.healog.mvc.diary.model.dao;

import java.util.List;

import com.healog.mvc.diary.model.dto.Pt;

public interface PtDao {

	int selectPt(int id);

	void insertPt(String trainerId, int diaryId, int time);

	Pt selectPtByDiaryId(int id);

	void updateTime(int id, int time);

	void deletePt(int id);

	void insertOrUpdatePt(String trainerId, int id, int time, int requestDiary, int requestState, int requestTime);

	List<Pt> selectRequestPtByTrainer(String trainerId);

	void updateRequestState(int diaryId, int num);
	
	void updateRequestStateDirect(int diaryId, int num);

	List<Pt> selectPtByUserId(String userId);

	List<Pt> selectRequestPtByUserId(String userId);
	
}
