package com.healog.mvc.diary.model.dao;

public interface PtDao {

	int selectPt(int id);

	void insertPt(String trainerId, int diaryId, int time);
	
}
