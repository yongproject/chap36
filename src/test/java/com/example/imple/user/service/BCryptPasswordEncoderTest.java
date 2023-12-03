package com.example.imple.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	void encode() {
		var encodeStr = encoder.encode("1234");
		System.out.println(encodeStr);
	}
//	$2a$10$hsQ1C4WrkZCFSRpBspKnnOCCT.Ko5dospHkHrYasx4koVUgzGiN7a
//	$2a$10$gzPHlHs79cDUGlmqEq11ae5f/X.zGUxy.xZQssr3aDgd9j69VVFU2


	
	@Test
	void match() {
		var success = encoder.matches("1234", "$2a$10$gzPHlHs79cDUGlmqEq11ae5f/X.zGUxy.xZQssr3aDgd9j69VVFU2");
		System.out.println(success);
		assertThat(success).isEqualTo(true);
	}
//		success = encoder.matches("1234", "$2a$10$OrbP1vfI5/WQQbMz1OiaMOZGvpEfL9yi91i3YbFXz5C67W47yffzi");
//		System.out.println(success);
//		assertThat(success).isEqualTo(true);
//		
//		success = encoder.matches("1234", "1234");
//		System.out.println(success);
//		assertThat(success).isEqualTo(false);
//	}
}
