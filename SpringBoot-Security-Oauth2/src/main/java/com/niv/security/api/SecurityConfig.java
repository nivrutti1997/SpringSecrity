package com.niv.security.api;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.oauth2Login();
	}
	
	 private ClientRegistration clietRegistration() {
		 return CommonOAuth2Provider.GITHUB.getBuilder("github")
				 .clientId("7841f83c6a401886cd67")
				 .clientSecret("b5a53d7849dcb8ba795a8af8c7e444f5670850e2")
				 .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				 .build();
	 }
	
	 @Bean
	 public ClientRegistrationRepository clientRepository() {
		 return new InMemoryClientRegistrationRepository(clietRegistration());
	 }
	
}
