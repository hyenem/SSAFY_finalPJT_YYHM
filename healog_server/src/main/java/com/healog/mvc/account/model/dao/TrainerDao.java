package com.healog.mvc.account.model.dao;

import java.util.List;

import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;

public interface TrainerDao {

	List<Gym> selectAllGyms();

	String selectIdByEmail(String email);

	int insertTrainer(Trainer trainer);
	
}
