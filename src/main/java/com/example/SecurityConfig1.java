package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfig1 { //security 적용 안했을 때와 똑같은 상태
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//보안 관련 설정
		http.csrf(csrf ->{
			csrf.disable();
		});
		
		http.cors(cors -> {
			cors.disable();
		});
		
		
		http.authorizeHttpRequests(request -> {
			request.anyRequest().permitAll();
		});
		
		
		
		return http.build();
	}
	
}
