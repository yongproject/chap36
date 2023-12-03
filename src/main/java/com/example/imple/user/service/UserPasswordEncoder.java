package com.example.imple.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

//@Component//(service로 해도 무관)
@Slf4j
public class UserPasswordEncoder implements PasswordEncoder { //match를 호출

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return rawPassword.toString();//단순히 비밀번호를 string으로. 원래는 암호화
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) { //rawPassword는 user가 입력, encodedPassword UserDetail에 등록된 데이터(DB조회)
		log.info("rawPassword = {}", rawPassword);
		log.info("encodedPassword = {}", encodedPassword);
		return encodedPassword.equals(encode(rawPassword)); //encoded된 password가 암호화된 password와 일치? false면 다르다고 판단
	}

}
