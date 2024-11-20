package com.healog.mvc.account.model.service;

import java.util.List;

import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;

public interface TrainerService {

	List<Gym> getAllGyms();

	boolean isValidId(String email);

	boolean signUp(Trainer trainer);

}
