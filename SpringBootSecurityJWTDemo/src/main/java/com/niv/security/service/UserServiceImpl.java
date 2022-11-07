package com.niv.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.niv.security.model.User;
import com.niv.security.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Integer saveUser(User user) {
		user.setPassword(
				encoder.encode(user.getPassword())
				);
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}
}
