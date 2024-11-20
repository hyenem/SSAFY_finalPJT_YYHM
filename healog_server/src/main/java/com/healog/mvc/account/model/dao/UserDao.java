package com.healog.mvc.account.model.dao;

import java.util.List;

import com.healog.mvc.account.model.dto.User;

public interface UserDao {

	String selectIdByEmail(String email);
	int insertUser(User user);
	User selectByEmail(String email);

}
