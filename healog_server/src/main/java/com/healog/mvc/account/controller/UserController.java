package com.healog.mvc.account.controller;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healog.mvc.account.model.dto.User;
import com.healog.mvc.account.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	UserController(UserService userServiceImpl){
		this.userService = userServiceImpl;
	}
	
	@PostMapping("/signup/id")
	public ResponseEntity<?> idCheck(@RequestBody User user){
		boolean result = userService.isValidId(user.getEmail());
		if (result) return new ResponseEntity<User>(user, HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> singUp(@RequestBody User user, @RequestBody boolean validEmail){
		
		if(!validEmail) return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		boolean result = userService.signUp(user);
		if (result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
