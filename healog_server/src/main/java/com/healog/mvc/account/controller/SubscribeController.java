package com.healog.mvc.account.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;
import com.healog.mvc.account.model.service.SubscribeService;
import com.healog.mvc.account.model.service.TrainerService;
import com.healog.mvc.account.model.service.UserService;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
	
	private SubscribeService subscribeService;
	private UserService userService;
	private TrainerService trainerService;
	public SubscribeController(SubscribeService subscribeService, UserService userService, TrainerService trainerService) {
		this.subscribeService = subscribeService;
		this.userService = userService;
		this.trainerService = trainerService;
	}
	
	@PostMapping
	public ResponseEntity<?> subscribe(@RequestBody Map<String, String> map){
		String trainerId = map.get("trainerId");
		String userId = map.get("userId");
		int subscribeMonth = Integer.parseInt(map.get("subscribeMonth"));
		boolean result = subscribeService.subscribe(trainerId, userId, subscribeMonth);
		if(result) {
			userService.changeTrainerExist(userId, 1);
			trainerService.addUserCount(trainerId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/trainer")
	public ResponseEntity<?> getTrainerInfo(@RequestParam("id") String userid){
		Trainer trainer = subscribeService.getTrainerInfo(userid);
		if(trainer!=null && trainer.getId()!=null) {
			return new ResponseEntity<Trainer>(trainer, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/gym")
	public ResponseEntity<?> getGymName(@RequestParam int id){
		Gym gym = subscribeService.getGymById(id);
		if(gym != null && gym.getId()!=0) {
			return new ResponseEntity<Gym>(gym, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PatchMapping("/check")
	public ResponseEntity<?> checkValid(@RequestParam String id){
		int num = subscribeService.checkValid(id);

		if(num==0) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}
	
	@PatchMapping("/cancel")
	public ResponseEntity<?> cancelSubscribe(@RequestBody Map<String, String> map){
		String userId = map.get("userId");
		String trainerId = map.get("trainerId");
		int num = subscribeService.cancelSubscribe(userId);
		userService.changeTrainerExist(userId, 0);
		trainerService.substractUserCount(trainerId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/follow")
	public ResponseEntity<?> follower(@RequestParam String id){
		List<Map<String, String>> followerList = subscribeService.getFollowerById(id);
		System.out.println(followerList);
		return new ResponseEntity<List<Map<String, String>>>(followerList, HttpStatus.OK);

	}
}
