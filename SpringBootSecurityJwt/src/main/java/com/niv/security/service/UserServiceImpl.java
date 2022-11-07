package com.niv.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.niv.security.model.User;
import com.niv.security.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Integer saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user).getId();
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username) ;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> op = findByUsername(username);
		if (op.isEmpty()) {
			throw new UsernameNotFoundException("User not found..");
		}
		User user = op.get();
		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),
				user.getRoles().stream()
				.map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList())
				);
	}

	
}
