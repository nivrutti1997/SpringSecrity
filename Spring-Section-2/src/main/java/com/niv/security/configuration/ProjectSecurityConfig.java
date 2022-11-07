package com.niv.security.configuration;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {

		http.
//		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().
		cors().configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));	
				config.setExposedHeaders(Arrays.asList("Authorization"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().disable()
//		.ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests()
			.antMatchers("/myAccount").authenticated()
			.antMatchers("/myBalance").authenticated()
			.antMatchers("/myLoans").authenticated()
//			.antMatchers("/myAccount").hasAuthority("UPDATE")
//			.antMatchers("/myBalance").hasAuthority("READ")
//			.antMatchers("/myLoans").hasAuthority("DELETE")
			.antMatchers("/myCards").authenticated()
			.antMatchers("/contact").permitAll()
			.antMatchers("/notices").permitAll()
			.and()
			.formLogin().and()
			.httpBasic();
			
	}
//		Configuration to deny all the request
//		http.authorizeRequests().anyRequest().denyAll();

	// configuration to permit all the requests.
//		 http.authorizeRequests().anyRequest().permitAll()
//		 .and().formLogin().and().httpBasic();

	/*
	 * protected void configure(AuthenticationManagerBuilder auth) throws Exception
	 * {
	 * 
	 * auth.inMemoryAuthentication().withUser("admin").password("12345").authorities
	 * ("admin").and().withUser("user")
	 * .password("12345").authorities("read").and().passwordEncoder(
	 * NoOpPasswordEncoder.getInstance()); }
	 */

	/*
	 * protected void configure(AuthenticationManagerBuilder auth) throws Exception
	 * {
	 * 
	 * InMemoryUserDetailsManager userDetailsManager = new
	 * InMemoryUserDetailsManager(); UserDetails user =
	 * User.withUsername("admin").password("12345").authorities("admin").build();
	 * UserDetails user1 =
	 * User.withUsername("user").password("12345").authorities("read").build();
	 * userDetailsManager.createUser(user); userDetailsManager.createUser(user1);
	 * auth.userDetailsService(userDetailsManager);
	 * 
	 * }
	 */

//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
