package com.healog.mvc.diary.model.dto;

import org.springframework.stereotype.Component;

@Component
public class Pt {
	private String trainerId;
	private int diaryId;
	private String requestDiary;
	private String date;
	private String RequestDate;
	private int requestState;
	private int time;
	private int requestTime;
	private String userName;
	
	public Pt(){}

	public Pt(String trainerId, int diaryId, String requestDiary, String date, String requestDate, int requestState,
			int time, int requestTime, String userName) {
		super();
		this.trainerId = trainerId;
		this.diaryId = diaryId;
		this.requestDiary = requestDiary;
		this.date = date;
		RequestDate = requestDate;
		this.requestState = requestState;
		this.time = time;
		this.requestTime = requestTime;
		this.userName = userName;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrainerId() {
		return trainerId;
	}



	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}



	public int getDiaryId() {
		return diaryId;
	}



	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}



	public String getRequestDiary() {
		return requestDiary;
	}



	public void setRequestDiary(String requestDiary) {
		this.requestDiary = requestDiary;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getRequestDate() {
		return RequestDate;
	}



	public void setRequestDate(String requestDate) {
		RequestDate = requestDate;
	}



	public int getRequestState() {
		return requestState;
	}



	public void setRequestState(int requestState) {
		this.requestState = requestState;
	}



	public int getTime() {
		return time;
	}



	public void setTime(int time) {
		this.time = time;
	}



	public int getRequestTime() {
		return requestTime;
	}



	public void setRequestTime(int requestTime) {
		this.requestTime = requestTime;
	}



	@Override
	public String toString() {
		return "Pt [trainerId=" + trainerId + ", diaryId=" + diaryId + ", requestDiary=" + requestDiary
				+ ", requestState=" + requestState + "]";
	}
	
	
}
