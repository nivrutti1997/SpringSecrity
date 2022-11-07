package com.niv.security.util;

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
	private String secretKey;
	
	//6. validate username in token and db, expDate
	public boolean validateToken(String token, String username) {
		String tokenUsername = getUsername(token);
		return username.equals(tokenUsername) && !isTokenExpired(token);
	}
	
	//5. Validate Exp Date
	public boolean isTokenExpired(String token) {
		return getExpiredDate(token).before(new Date(System.currentTimeMillis()));
	}
	
	//4. read subject/username
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	//3. read Exp Date 
	public Date getExpiredDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	//Read Claims
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secretKey.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	//Generate JWT token
	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("Nivrutti")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();
	}
}
