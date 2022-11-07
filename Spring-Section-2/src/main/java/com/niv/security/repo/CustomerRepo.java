package com.niv.security.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niv.security.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	List<Customer> findByEmail(String email);
}
