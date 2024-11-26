package com.healog.mvc.diary.model.service;

import java.util.List;

import com.healog.mvc.diary.model.dto.Pt;

public interface PtService {

	int getPt(String userId, int year, int month, int day);

	void setPt(String trainerId, int diaryId, int time);

	Pt getPtInfo(String userId, int year, int month, int day);

	void updateTime(int id, int time);

	void deletePt(int id);

	void requestInsert(int id, int time, int i);

	List<Pt> getRequestByTrainer(String trainerId);

	void acceptRequest(int diaryId);

	void denyRequest(int diaryId);

	List<Pt> getRequestByUser(String userId);

	void checkResponse(int diaryId);

	void requestUpdate(int diaryId, int time);

	void requestDelete(int diaryId);

	List<Pt> getPtByUser(String userId);


}
