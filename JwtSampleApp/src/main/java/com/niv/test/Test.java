package com.niv.test;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Test {

	public static void main(String[] args) {
		String key = "NIT";
		String token = Jwts.builder()
						.setId("A5266")
						.setSubject("Ajay")
						.setIssuer("NareshIt")
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
						.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(key.getBytes()))
						.compact();
		System.out.println(token);
		
		Claims c = Jwts.parser().
		setSigningKey(Base64.getEncoder().encode(key.getBytes()))
		.parseClaimsJws(token).getBody();
		System.out.println(c.getId());
		
						
	}
}
