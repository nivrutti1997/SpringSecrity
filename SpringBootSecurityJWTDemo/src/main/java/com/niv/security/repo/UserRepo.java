package com.niv.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niv.security.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
}
