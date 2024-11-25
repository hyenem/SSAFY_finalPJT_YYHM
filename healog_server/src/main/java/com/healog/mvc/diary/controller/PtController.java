package com.healog.mvc.diary.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healog.mvc.diary.model.dto.Pt;
import com.healog.mvc.diary.model.service.PtService;


@Controller
@RequestMapping("/pt")
public class PtController {

	private PtService ptService;
	public PtController(PtService ptService) {
		this.ptService = ptService;
	}

	@GetMapping
	public ResponseEntity<?> getPt(@RequestParam("userId") String userId,
			@RequestParam("year") int year,
			@RequestParam("month") int month,
			@RequestParam("day") int day){
		try {
			boolean isPt = ptService.getPt(userId, year, month, day);
			return new ResponseEntity<>(isPt, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@PostMapping
	public ResponseEntity<?> setPt(@RequestBody Map<String, String> map){
		String trainerId = map.get("trainerId");
		int diaryId = Integer.parseInt(map.get("id"));
		int time = Integer.parseInt(map.get("time"));
		try {
			ptService.setPt(trainerId, diaryId, time);			
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/info")
	public ResponseEntity getPtInfo(@RequestParam("userId") String userId,
									@RequestParam("year") int year,
									@RequestParam("month") int month,
									@RequestParam("day") int day){
		try {
			Pt pt = ptService.getPtInfo(userId, year, month, day);
			return new ResponseEntity<>(pt, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}
	
	@PatchMapping("/time")
	public ResponseEntity<?> updateTime(@RequestBody Map<String, String> map){
		int id = Integer.parseInt(map.get("id"));
		int time = Integer.parseInt(map.get("time"));
		try {
			ptService.updateTime(id, time);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deletPt(@RequestParam("id") int id){
		try {
			ptService.deletePt(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
