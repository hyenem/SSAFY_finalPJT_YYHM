package com.healog.mvc.account.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;

public interface TrainerDao {

	List<Trainer> selectTrainersByName(String name);

	List<Gym> selectAllGyms();

	String selectIdByEmail(String email);

	int insertTrainer(Trainer trainer);

	Trainer selectByEmail(String email);

	Trainer selectAllById(String id);

	List<Trainer> selectAllTrainers();

	void updateUserCount(@Param("id") String id,@Param("count") int count);

	
	String selectTrainerByDiary(int id);
}

