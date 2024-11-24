package com.healog.mvc.diary.model.service;

public interface PtService {

	boolean getPt(String userId, int year, int month, int day);

	void setPt(String trainerId, int diaryId, int time);

}
