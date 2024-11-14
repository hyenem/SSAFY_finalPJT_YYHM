package com.healog.mvc.account.model.dao;

import com.healog.mvc.account.model.dto.User;

public interface UserDao {

	int searchByEmail(String email);

	int insertUser(User user);


}
