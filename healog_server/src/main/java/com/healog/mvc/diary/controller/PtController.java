package com.healog.mvc.diary.controller;

import java.util.List;
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
			int isPt = ptService.getPt(userId, year, month, day);
			return new ResponseEntity<Integer>(isPt, HttpStatus.OK);
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
	
	@PostMapping("/request/insert")
	public ResponseEntity<?> requestInsert(@RequestBody Map<String, String> map){
		int id = Integer.parseInt(map.get("id"));
		int time = Integer.parseInt(map.get("time"));
		try {
			ptService.requestInsert(id, time, 3);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/request/trainer")
	public ResponseEntity<?> getRequstByTrainer(@RequestParam String trainerId){
		try {
			List<Pt> ptList = ptService.getRequestByTrainer(trainerId);
			return new ResponseEntity<List<Pt>>(ptList, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();	
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/request/user")
	public ResponseEntity<?> getRequstByUser(@RequestParam String userId){
		try {
			List<Pt> ptList = ptService.getRequestByUser(userId);
			return new ResponseEntity<List<Pt>>(ptList, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();	
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getPtByUser(@RequestParam String userId){
		try {
			List<Pt> ptList = ptService.getPtByUser(userId);
			return new ResponseEntity<List<Pt>>(ptList, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();	
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PatchMapping("/accept")
	public ResponseEntity<?> accept(@RequestBody Map<String, String> map){
		int diaryId = Integer.parseInt(map.get("diaryId"));
		try {
			ptService.acceptRequest(diaryId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PatchMapping("/deny")
	public ResponseEntity<?> deny(@RequestBody Map<String, String> map){
		int diaryId = Integer.parseInt(map.get("diaryId"));
		try {
			ptService.denyRequest(diaryId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PatchMapping("/check")
	public ResponseEntity<?> check(@RequestBody Map<String, String> map){
		int diaryId = Integer.parseInt(map.get("diaryId"));
		try {
			ptService.checkResponse(diaryId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PatchMapping("/request/update")
	public ResponseEntity<?> requestUpdate(@RequestBody Map<String, String> map){
		int diaryId = Integer.parseInt(map.get("id"));
		int time = Integer.parseInt(map.get("time"));
		try {
			ptService.requestUpdate(diaryId, time);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/request/delete")
	public ResponseEntity<?> requestDelete(@RequestParam("id") int id){
		try {
			ptService.requestDelete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
