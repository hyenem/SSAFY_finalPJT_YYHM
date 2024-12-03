package com.healog.mvc.account.model.service;

import java.util.List;
import java.util.Map;

import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;

public interface SubscribeService {

	boolean subscribe(String trainerId, String userId, int subscribeMonth);

	Trainer getTrainerInfo(String userid);

	Gym getGymById(int id);

	int checkValid(String id);

	int cancelSubscribe(String userId);

	List<Map<String, String>> getFollowerById(String trainerId);

}
