package com.niv.security.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.niv.security.model.User;
import com.niv.security.repo.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Calling userDetailsService");
		Optional<User> optUser = repo.findByUsername(username);
		if (optUser.isEmpty()) {
			throw new UsernameNotFoundException("User not exist..");
		}
		User user = optUser.get();
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), 
				user.getRoles().stream()
				.map(role->new SimpleGrantedAuthority(role))
				.collect(Collectors.toList())
				);
	}

	
}
