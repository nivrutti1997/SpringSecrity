package com.niv.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niv.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByUsername(String username);
}
