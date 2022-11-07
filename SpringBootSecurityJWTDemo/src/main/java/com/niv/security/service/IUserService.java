package com.niv.security.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.niv.security.model.User;

public interface IUserService {
	
	Integer saveUser(User user);
	Optional<User> findByUsername(String username);
	
}
