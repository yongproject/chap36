package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

//@Configuration
public class SecurityConfig3 {
	
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
		http.authorizeHttpRequests(request -> { //request에 대해 어떻게 반응할지를 설정하는 것이다.
			request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll(); //forward 허용
			request.requestMatchers("/", "/error").permitAll(); //로그인 하지 않아도 home 화면은 접근을 허용한다.
			request.requestMatchers("/webjars/**").permitAll(); //webjars 이하의 경로는 모두 허용
			
			request.requestMatchers("/dept/list", 	  "/dept/detail/{key}").permitAll();
			request.requestMatchers("/emp/list", 	  "/emp/detail/{key}").permitAll();
			request.requestMatchers("/salgrade/list", "/salgrade/detail/{key}").permitAll();
			
			request.anyRequest().authenticated(); //로그인 페이지를 제외하고는 모두 인증이 필요하다.
			//request.anyRequest().permitAll();
		});
		
		//로그인 방식 설정
		http.formLogin(login -> { 
			login.loginPage("/user/login"); //로그인 url 설정
//			login.loginProcessingUrl("/user/login"); 로그인 실행하는 url, post방식, 생략해도 똑같음
			login.defaultSuccessUrl("/", true); //로그인 성공하면 항상 home으로 가도록
			login.failureHandler((request, response, e) -> { //로그인 실패 시 처리. e가 실패했을 때 에러정보
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/user/login-fail").forward(request, response);
			});
			login.permitAll(); //로그인 관련 페이지는 모두 permit
		});
		
		http.logout(logout -> {
			logout.logoutUrl("/user/logout");
		});
		
		return http.build();
	}
	
}
