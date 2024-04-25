package com.pp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.security.auth.message.callback.SecretKeyCallback.Request;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
private UserDetailsService userDetailsService;
	@Bean
	public AuthenticationProvider authentice() {
		
		DaoAuthenticationProvider daoAuthProvider=new DaoAuthenticationProvider();
		
		
		daoAuthProvider.setUserDetailsService(userDetailsService);
		daoAuthProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return daoAuthProvider;
		
	}
	@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		http.csrf(Customizer -> Customizer.disable());
		http.authorizeHttpRequests(request->request.anyRequest().authenticated());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
}
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user=User.withDefaultPasswordEncoder().username("user").password("@1234").roles("user").build();
//		
//		UserDetails admin=User.withDefaultPasswordEncoder().username("prakash").password("@5678").roles("admin").build();
//		return new InMemoryUserDetailsManager(user,admin);
//	}
}
