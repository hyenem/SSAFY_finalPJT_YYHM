package com.healog.mvc.account.model.service;

import com.healog.mvc.account.model.dto.User;

public interface UserService {

	boolean isValidId(String email);

	boolean signUp(User user);

}
