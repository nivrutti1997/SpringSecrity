package com.niv.test;

import io.jsonwebtoken.Claims;

public class Test1 {

	public static void main(String[] args) {
		JwtUtil ju = new JwtUtil();
		String token = ju.generateToken("A9847", "Nivrutti", "Clairvoyant");
		System.out.println(token);
		
		Claims claims = ju.getToken("Clairvoyant", token);
		String id = claims.getId();
		System.out.println(id);
		System.out.println(claims.getIssuer());
		System.out.println(claims.getExpiration());
	}
}
