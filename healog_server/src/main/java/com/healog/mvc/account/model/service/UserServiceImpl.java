package com.healog.mvc.account.model.service;

import org.springframework.stereotype.Service;

import com.healog.mvc.account.model.dao.UserDao;
import com.healog.mvc.account.model.dto.User;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	UserServiceImpl(UserDao userDao){
		this.userDao = userDao;
	}
	
	@Override
	public boolean isValidId(String email) {
		return userDao.searchByEmail(email) ==  null? true : false;
	}

	@Override
	public boolean signUp(User user) {
		return userDao.insertUser(user)>0 ? true:false;
	}

}
