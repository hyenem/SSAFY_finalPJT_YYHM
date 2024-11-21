package com.healog.mvc.diary.model.dto;

public class CategoryDto {
    private int id;
    private String exercise;
    private String exerciseArea;

    public CategoryDto() {
		// TODO Auto-generated constructor stub
	}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
