package com.healog.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healog.mvc.entity.Excercise;
import com.healog.mvc.repository.ExcerciseRepository;

@Service
public class ExcerciseService {
	
	private ExcerciseRepository excerciseRepository;
	
	public ExcerciseService(ExcerciseRepository excerciseRepository) {
		this.excerciseRepository = excerciseRepository;
	}
	// 운동 기록 저장
	public Excercise saveExcercise(Excercise excercise ) {
		return excerciseRepository.save(excercise);
	}
	// 운동 기록 수정
	public Excercise updateExcercise(Excercise excercise) {
		return excerciseRepository.save(excercise);
	}
	// 운동 기록 삭제
	public void deleteExcercise(Integer id) {
		excerciseRepository.deleteById(id);
	}
	// 운동 기록 조회
	public Excercise getExcerciseById(Integer id) {
		return excerciseRepository.findById(id).orElse(null);
	}
	// 특정 일자 다이어리에 연결된 운동 기록 조회
	public List<Excercise> getExcercisesById(Integer diaryId) {
		return excerciseRepository.findByDiaryId(diaryId);
	}
}
