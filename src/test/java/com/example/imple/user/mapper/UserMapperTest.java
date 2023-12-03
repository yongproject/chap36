package com.example.imple.user.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.imple.user.model.User;

@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	UserMapper mapper;
	
	@Test
	void selectById() {
		var user = mapper.selectById("python");
		System.out.println(user);
		assertThat(user).isNotNull();
		
		user = mapper.selectById("xxx");
		System.out.println(user);
		assertThat(user).isNull();
	}
	
	@Test
	void selectByPhoneNumber() {
		assertThat(mapper.selectByPhoneNumber("000")).isNull();
	}
}
