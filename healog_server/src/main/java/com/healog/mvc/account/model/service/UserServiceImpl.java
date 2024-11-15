package com.healog.mvc.account.model.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.healog.mvc.account.model.dao.UserDao;
import com.healog.mvc.account.model.dto.User;
import com.healog.mvc.util.EncryptUtil;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	private final EncryptUtil encryptUtil;
	UserServiceImpl(UserDao userDao, EncryptUtil encryptUtil){
		this.userDao = userDao;
		this.encryptUtil = encryptUtil;
	}
	
	@Override
	public boolean isValidId(String email) {
		return userDao.searchByEmail(email) ==  null? true : false;
	}

	@Override
	public boolean signUp(User user) {
		
		user.setId(UUID.randomUUID().toString());
		String salt = encryptUtil.getSalt();
		user.setSalt(salt);
		user.setPassword(encryptUtil.getEncrypt(user.getPassword(), salt));
		
		return userDao.insertUser(user)>0 ? true:false;
	}

}
