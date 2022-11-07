package com.niv.security.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.niv.security.entities.Customer;
import com.niv.security.repo.CustomerRepo;

public class BankAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepo customerRepo;
		
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<Customer> customer = customerRepo.findByEmail(username);
		if (customer.size()>0) {
			if (passwordEncoder.matches(password, customer.get(0).getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username,password, authorities);
			}else {
				throw new BadCredentialsException("Invalid Password...!!");
			}
		}else {
			throw new BadCredentialsException("No user registered with this email..!!");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {	
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
	
}
