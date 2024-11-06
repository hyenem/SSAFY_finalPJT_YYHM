package com.healog.mvc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Excercise {
	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키값 생성
	private Integer id;
	private Integer categoryId;
	private Integer diaryId;
	private Integer weight;
	private Integer count;
	private Integer set;
	private String postureImg;
	private Boolean done = false;
	
	public Excercise() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getSet() {
		return set;
	}

	public void setSet(Integer set) {
		this.set = set;
	}

	public String getPostureImg() {
		return postureImg;
	}

	public void setPostureImg(String postureImg) {
		this.postureImg = postureImg;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	
	
	
}
