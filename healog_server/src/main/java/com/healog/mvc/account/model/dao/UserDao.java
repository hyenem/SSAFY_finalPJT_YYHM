package com.healog.mvc.account.model.dao;

import java.util.List;

import com.healog.mvc.account.model.dto.User;

public interface UserDao {

	List<String> searchByEmail(String email);
	int insertUser(User user);


}
