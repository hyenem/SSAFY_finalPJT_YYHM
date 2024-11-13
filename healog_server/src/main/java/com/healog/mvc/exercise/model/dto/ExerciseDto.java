package com.healog.mvc.exercise.model.dto;

public class ExerciseDto {
    private int id; // 운동 ID
    private int categoryId; // 운동 카테고리 ID
    private int diaryId; // 연결된 다이어리 ID
    private Integer weight; // 운동 무게
    private Integer count; // 반복 횟수
    private Integer set; // 세트 수
    private String postureImg; // 자세 이미지 URL
    private int done; // 운동 완료 여부 (0: 미완료, 1: 완료)

    public ExerciseDto() {}

    public ExerciseDto(int id, int categoryId, int diaryId, Integer weight, Integer count, Integer set, String postureImg, int done) {
        this.id = id;
        this.categoryId = categoryId;
        this.diaryId = diaryId;
        this.weight = weight;
        this.count = count;
        this.set = set;
        this.postureImg = postureImg;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

}
