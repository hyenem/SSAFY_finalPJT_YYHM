package com.healog.mvc.diary.model.dto;

import org.springframework.stereotype.Component;

@Component
public class Pt {
	private String trainer_id;
	private int diary_id;
	private String requestDate;
	private int requestState;
	private int time;
	
	public Pt(){}

	public Pt(String trainer_id, int diary_id, String requestDate, int requestState, int time) {
		super();
		this.trainer_id = trainer_id;
		this.diary_id = diary_id;
		this.requestDate = requestDate;
		this.requestState = requestState;
		this.time = time;
	}

	public String getTrainer_id() {
		return trainer_id;
	}

	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}

	public int getDiary_id() {
		return diary_id;
	}

	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
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

	@Override
	public String toString() {
		return "Pt [trainer_id=" + trainer_id + ", diary_id=" + diary_id + ", requestDate=" + requestDate
				+ ", requestState=" + requestState + "]";
	}
	
	
}
