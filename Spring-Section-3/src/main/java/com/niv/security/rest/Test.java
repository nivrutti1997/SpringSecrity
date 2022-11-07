package com.niv.security.rest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		BCryptPasswordEncoder ed = new BCryptPasswordEncoder();
		String syed = ed.encode("syed");
		System.out.println(syed);
		String sam = ed.encode("sam");
		System.out.println(sam);
		String jai = ed.encode("jai");
		System.out.println(jai);
		
		//syed = $2a$10$0Z0L.hdlNUUXvWteoJuDV..7W8UcsqRzyBVTOuvEy8hwVzIf4P3ye
		//sam = $2a$10$DQY2LrpYAF5/2MnwcEXqmu5t.zMKwPlaglQPwRdb2NEc368qt0/tq
		//jai = $2a$10$z8YP6f2nkI0vV0nJ1BHDteO96AxwnJdAGSqxX/1p8U75fP5z.b0S.
		
	}
}
