package com.healog.mvc.account.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healog.mvc.account.model.service.TrainerService;
import com.healog.mvc.account.model.service.UserService;
import com.healog.mvc.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@RequestMapping("/check")
public class CheckController {
	private JwtUtil jwtUtil;
	private UserService userService;
	private TrainerService trainerService;
	
	public CheckController(JwtUtil jwtUtil, UserService userService, TrainerService trainerService) {
		this.jwtUtil = jwtUtil;
		this.userService = userService;
		this.trainerService = trainerService;
	}
	
	
	@GetMapping
	public ResponseEntity<?> checkUser(@RequestHeader("access-token") String token) {
		try {
			Jws<Claims> claims = jwtUtil.vaildate(token);
			Map<String, String> map = new HashMap<>();
			String type = (String)claims.getPayload().get("type");
			String email = (String)claims.getPayload().get("email");
			String id = "";
			if(type.equals("user"))	id = userService.getUserInfo(email);
			else id = trainerService.getIdByEmail(email);
			map.put("id", id);
			map.put("type", type);
			System.out.println(map);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
        } catch (Exception e) {
            // 서명 검증 실패 또는 유효하지 않은 토큰
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
	}
}
