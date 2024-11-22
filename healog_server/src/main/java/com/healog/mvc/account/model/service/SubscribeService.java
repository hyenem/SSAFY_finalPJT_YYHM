package com.healog.mvc.account.model.service;

import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;

public interface SubscribeService {

	boolean subscribe(String trainerId, String userId, int subscribeMonth);

	Trainer getTrainerInfo(String userid);

	Gym getGymById(int id);

	int checkValid(String id);

}
