package com.niv.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niv.security.model.User;
import com.niv.security.model.UserRequest;
import com.niv.security.model.UserResponse;
import com.niv.security.service.IUserService;
import com.niv.security.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil util;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer userId = userService.saveUser(user);
		String body = "User '"+userId+"' Saved";
		return ResponseEntity.ok(body);	
	}
	
	
	//Validate user and generate token
	@PostMapping("/login")
	public ResponseEntity<UserResponse> generateToken(@RequestBody UserRequest request){
		System.out.println("Verifying user");
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), 
						request.getPassword()
						)
				); 	
		String token = util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token, "Successfull!! Generated by Nivrutti.."));
	}
	
	
	@GetMapping("/welcome")
	public ResponseEntity<String> msg(Principal principal){
		return ResponseEntity.ok("Welcome user : "+principal.getName());
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> msgHello(){
		return ResponseEntity.ok("Hello Dear...");
	}
	
}
