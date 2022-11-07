package com.niv.security.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.niv.security.entities.Customer;
import com.niv.security.repo.CustomerRepo;
import com.niv.security.repo.SecurityCustomer;

@Service
public class BankUserDetails implements UserDetailsService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			List<Customer> customer = customerRepo.findByEmail(username);
			if (customer.size()==0) {	
				throw new UsernameNotFoundException("User details not found for the user : "+username);
			}
		return new SecurityCustomer(customer.get(0));
	}

}
