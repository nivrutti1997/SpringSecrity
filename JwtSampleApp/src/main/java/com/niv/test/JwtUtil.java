package com.niv.test;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	//generate token
	public String generateToken(String id, String subject, String key) {
		return Jwts.builder()
				.setId(id)
				.setSubject(subject)
				.setIssuer("NareshIt")
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(10)))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(key.getBytes()))
				.compact();
				
	}
	//get claims
	public Claims getToken(String key,String token) {
		return Jwts.parser().setSigningKey(Base64.getEncoder().encode(key.getBytes()))
				.parseClaimsJws(token).getBody(); 
	}
}
