package com.healog.mvc.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healog.mvc.account.model.dto.Gym;
import com.healog.mvc.account.model.dto.Trainer;
import com.healog.mvc.account.model.dto.User;
import com.healog.mvc.account.model.service.TrainerService;
import com.healog.mvc.util.JwtUtil;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final JwtUtil jwtUtil;

    public TrainerController(TrainerService trainerService, JwtUtil jwtUtil) {
        this.trainerService = trainerService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/gym")
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
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map){
		Trainer loginTrainer = trainerService.checkLogin(map.get("email"), map.get("password"));
		if(loginTrainer.getEmail()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(loginTrainer.getId()==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}

		Map<String, Object> result = new HashMap<>();
		result.put("access-token", jwtUtil.createToken(map.get("email"), map.get("type")));
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getTrainer(@RequestParam String id){
		Trainer trainer = trainerService.getTrainerById(id);
		if(trainer != null) {
			return new ResponseEntity<Trainer>(trainer, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllTrainer(){
		List<Trainer> trainers = trainerService.getAllTrainer();
		if(trainers != null && trainers.size()!=0) {
			return new ResponseEntity<List<Trainer>>(trainers, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchTrainerByName(@RequestParam String name){
		List<Trainer> trainers = trainerService.searchTrainerByName(name);
		if(trainers !=null && trainers.size()!=0) {
			return new ResponseEntity<List<Trainer>>(trainers, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
