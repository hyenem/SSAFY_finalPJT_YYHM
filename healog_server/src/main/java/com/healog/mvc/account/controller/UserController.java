package com.healog.mvc.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healog.mvc.account.model.dto.User;
import com.healog.mvc.account.model.service.UserService;
import com.healog.mvc.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final JwtUtil jwtUtil;
	UserController(UserService userService, JwtUtil jwtUtil){
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/signup/id")
	public ResponseEntity<?> idCheck(@RequestBody User user){
		boolean result = userService.isValidId(user.getEmail());
		if (result) return new ResponseEntity<String>(user.getEmail(), HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> singUp(@RequestBody User user){
		
		System.out.println(user);
		
		boolean result = userService.signUp(user);
		if (result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		User loginUser = userService.checkLogin(user.getEmail(), user.getPassword());
		if(loginUser.getEmail()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(loginUser.getId()==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}

		Map<String, Object> result = new HashMap<>();
		result.put("id", loginUser.getId());
		result.put("name", loginUser.getName());
		result.put("email", loginUser.getEmail());
		result.put("gender", loginUser.getGender());
		result.put("access-token", jwtUtil.createToken(loginUser));
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/login/validate")
	public ResponseEntity<?> validate(@RequestBody Map<String, String> map){
		String token = map.get("access-token");
		try {
			jwtUtil.vaildate(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} finally {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		
	}
	
}
