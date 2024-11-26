package com.healog.mvc.diary.model.dto;

public class DiaryDto {
	
	private int id;
	private int year;
	private int month;
	private int day;
	private String userId;
	private String condition;
	private String feedback;
	
	public DiaryDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DiaryDto(int id, int year, int month, int day, String userId, String condition, String feedback) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.userId = userId;
		this.condition = condition;
		this.feedback = feedback;
	}


	public String getFeedback() {
		return feedback;
	}


	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}


	@Override
	public String toString() {
		return "DiaryDto [id=" + id + ", year=" + year + ", month=" + month + ", day=" + day + ", userId=" + userId
				+ ", condition=" + condition + "]";
	}
	
	

}
