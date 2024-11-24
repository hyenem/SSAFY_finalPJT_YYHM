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
		return userDao.selectIdByEmail(email)==null || userDao.selectIdByEmail(email).length()==0? true : false;
	}

	@Override
	public boolean signUp(User user) {
		
		user.setId(UUID.randomUUID().toString());
		String salt = encryptUtil.getSalt();
		user.setSalt(salt);
		user.setPassword(encryptUtil.getEncrypt(user.getPassword(), salt));
		
		return userDao.insertUser(user)>0 ? true:false;
	}

	@Override
	public User checkLogin(String email, String password) {
		User checkingUser = userDao.selectByEmail(email);
		User loginUser = new User();
		//email자체가 없으면 null로 return
		if(checkingUser!=null) {
			loginUser.setEmail(email);
			//일단 email은 저장
			//그리고 비밀번호 채크한 다음에 나머지 정보들 저장
			String encryptPassword = encryptUtil.getEncrypt(password, checkingUser.getSalt());
			if(checkingUser.getPassword().equals(encryptPassword)) {
				loginUser.setName(checkingUser.getName());
				loginUser.setEmail(checkingUser.getEmail());
				loginUser.setId(checkingUser.getId());
				loginUser.setGender(checkingUser.getGender());
			}
		}
		return loginUser;
	}

	@Override
	public String getUserInfo(String email) {
		String id= userDao.selectIdByEmail(email);
		return id;
	}

	@Override
	public User getUserById(String id) {
		User user = userDao.selectAllById(id);
		if(user!=null) {
			user.setPassword(null);
			user.setSalt(null);
			user.setCreatedAtDatetime(null);
		}
		return user;
	}

	@Override
	public void changeTrainerExist(String id, int exist) {
		userDao.updateTrainerExist(id, exist);
	}

}
