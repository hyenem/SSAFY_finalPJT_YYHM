package com.healog.mvc.diary.model.dto;

public class ExerciseDto {
    private int id; // 운동 ID
    private int categoryId;
    private int diaryId; // 연결된 다이어리 ID
    private Integer weight; // 운동 무게
    private Integer count; // 반복 횟수
    private Integer set; // 세트 수
    private String postureImg; // 자세 이미지 URL
    private int done; // 운동 완료 여부 (0: 미완료, 1: 완료)
    
    private String exercise; // 운동 이름
    private String exerciseArea; // 운동 부위
    
    public ExerciseDto() {}
    
    

	public String getExercise() {
		return exercise;
	}



	public void setExercise(String exercise) {
		this.exercise = exercise;
	}



	public String getExerciseArea() {
		return exerciseArea;
	}



	public void setExerciseArea(String exerciseArea) {
		this.exerciseArea = exerciseArea;
	}



	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
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

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }



	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
    
    

}
