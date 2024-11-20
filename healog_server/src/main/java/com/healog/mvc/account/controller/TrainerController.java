package com.healog.mvc.account.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;
import com.healog.mvc.account.model.service.TrainerService;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/signup/gym")
    public ResponseEntity<List<Gym>> getAllGyms() {
    	System.out.println("here");
        List<Gym> gyms = trainerService.getAllGyms();
        return ResponseEntity.status(HttpStatus.OK).body(gyms);
    }
    
	@PostMapping("/signup/id")
	public ResponseEntity<?> idCheck(@RequestBody Map<String, String> map){
		boolean result = trainerService.isValidId(map.get("email"));
		if (result) return new ResponseEntity<String>(map.get("email"), HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> singUp(@RequestBody Trainer trainer){
		System.out.println(trainer);
		boolean result = trainerService.signUp(trainer);
		if (result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
