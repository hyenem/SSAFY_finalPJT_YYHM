package com.healog.mvc.dto;

public class Excercise {
	private Integer id;
	private Integer categoryId;
	private Integer weight;
	private Integer count;
	private Integer set;
	private String postureImg;
	private Boolean done;
	
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
