package com.niv.security.service;

import java.util.Optional;

import com.niv.security.model.User;

public interface IUserService {
	
	public Integer saveUser(User user);
	public Optional<User> findByUsername(String username);
}
