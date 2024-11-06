package com.healog.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healog.mvc.entity.*;

@Repository
public interface ExcerciseRepository extends JpaRepository<Excercise, Integer> {
	List<Excercise> findByDiaryId(@Param("diaryId") Integer diaryId);
	// 메서드 이름으로 쿼리 자동 생성
}
