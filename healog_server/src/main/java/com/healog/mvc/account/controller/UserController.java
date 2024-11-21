package com.healog.mvc.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healog.mvc.account.model.dto.User;
import com.healog.mvc.account.model.service.UserService;
import com.healog.mvc.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	public UserController(UserService userService, JwtUtil jwtUtil){
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
	public ResponseEntity<?> login(@RequestBody Map<String, String> map){
		User loginUser = userService.checkLogin(map.get("email"), map.get("password"));
		if(loginUser.getEmail()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(loginUser.getId()==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}

		Map<String, Object> result = new HashMap<>();
		result.put("access-token", jwtUtil.createToken(map.get("email"), map.get("type")));
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getUser(@RequestParam String id){
		System.out.println("here");
		User user = userService.getUserById(id);
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
