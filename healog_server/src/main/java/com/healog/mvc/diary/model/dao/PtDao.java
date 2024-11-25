package com.healog.mvc.diary.model.dao;

import com.healog.mvc.diary.model.dto.Pt;

public interface PtDao {

	int selectPt(int id);

	void insertPt(String trainerId, int diaryId, int time);

	Pt selectPtByDiaryId(int id);

	void updateTime(int id, int time);

	void deletePt(int id);
	
}
