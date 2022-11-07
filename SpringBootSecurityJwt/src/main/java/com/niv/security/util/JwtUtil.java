package com.niv.security.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;
	
	//Validate username in token and database, expdate
	public boolean validateToken(String token, String username) {
		String tokenUsername = getSubject(token);
		return username.equals(tokenUsername) && !isTokenExpired(token);
	}
	
	//Validate exp date
	public boolean isTokenExpired(String token) {
		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	//read subject/username
	public String getSubject(String token) {
		return getClaims(token).getSubject();
	}
	
	//Read exp date
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	//Claim token
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(secret.getBytes()))
				.parseClaimsJws(token).getBody();
	}
	
	
	//Generate token
	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(secret)
				.setIssuer("Nivrutti")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secret.getBytes()))
				.compact();
	}
	
	
}
