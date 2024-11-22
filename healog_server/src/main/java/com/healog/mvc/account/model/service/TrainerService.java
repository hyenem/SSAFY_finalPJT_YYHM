package com.healog.mvc.account.model.service;

import java.util.List;

import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;
import com.healog.mvc.account.model.dto.User;

public interface TrainerService {

	List<Gym> getAllGyms();

	boolean isValidId(String email);

	boolean signUp(Trainer trainer);

	Trainer checkLogin(String string, String string2);

	String getIdByEmail(String email);

	Trainer getTrainerById(String id);

	List<Trainer> getAllTrainer();

	List<Trainer> searchTrainerByName(String name);

	void addUserCount(String trainerId);

	void substractUserCount(String trainerId);
}
