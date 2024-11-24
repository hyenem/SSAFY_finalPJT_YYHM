package com.healog.mvc.diary.model.dto;

public class MealDto {

    private int id;
    private String breakfastImg; // Breakfast image
    private String lunchImg; // Lunch image
    private String dinnerImg; // Dinner image
    private int diaryId; // Diary ID (foreign key)

    public MealDto() {
		// TODO Auto-generated constructor stub
	}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreakfastImg() {
        return breakfastImg;
    }

    public void setBreakfastImg(String breakfastImg) {
        this.breakfastImg = breakfastImg;
    }

    public String getLunchImg() {
        return lunchImg;
    }

    public void setLunchImg(String lunchImg) {
        this.lunchImg = lunchImg;
    }

    public String getDinnerImg() {
        return dinnerImg;
    }

    public void setDinnerImg(String dinnerImg) {
        this.dinnerImg = dinnerImg;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    @Override
    public String toString() {
        return "MealDto{" +
                "id=" + id +
                ", breakfastImg='" + breakfastImg + '\'' +
                ", lunchImg='" + lunchImg + '\'' +
                ", dinnerImg='" + dinnerImg + '\'' +
                ", diaryId=" + diaryId +
                '}';
    }
}
