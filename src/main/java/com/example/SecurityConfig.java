package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//보안 관련 설정
		http.csrf(csrf ->{
			csrf.disable();
		});
		
		http.cors(cors -> {
			cors.disable();
		});
		
		//url의 허용여부 결정
		http.authorizeHttpRequests(request -> { 
			request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll(); 
			request.requestMatchers("/", "/error").permitAll(); 
			request.requestMatchers("/webjars/**").permitAll(); 
			request.requestMatchers("/img/**").permitAll();
			request.requestMatchers("/user/login/img/**").permitAll();
			request.requestMatchers("/user/update/**").permitAll();
			
			
			request.requestMatchers("/user/create", "/user/success").permitAll();
			request.requestMatchers("/user/select/**").permitAll();
			request.requestMatchers("/dept/list", 	  "/dept/detail/{key}").permitAll();
			request.requestMatchers("/emp/list", 	  "/emp/detail/{key}").permitAll();
			request.requestMatchers("/salgrade/list", "/salgrade/detail/{key}").permitAll();
			
			request.requestMatchers("/dept/create",
									"/dept/update",
									"/dept/delete").hasAnyRole("ADMIN"); 
			
			request.anyRequest().authenticated(); 
			//request.anyRequest().permitAll();
		});
		
		
		http.formLogin(login -> { 
			login.loginPage("/user/login");
//			login.loginProcessingUrl("/user/login"); 
			login.defaultSuccessUrl("/", true); 
			login.failureHandler((request, response, e) -> { 
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/user/login-fail").forward(request, response);
			});
			login.permitAll(); 
		});
		
		http.logout(logout -> {
			logout.logoutUrl("/user/logout");
		});
		
		//Remember Me
		http.rememberMe(remember -> {
			remember.alwaysRemember(false); //true로 하면 무조건 자동 로그인
			remember.tokenValiditySeconds(60*60*24); //얼마나 유지할지, 초 단위(안하면 14일 기본)
			remember.userDetailsService(userDetailsService); 
		});
		
		return http.build();
	}
	
}
