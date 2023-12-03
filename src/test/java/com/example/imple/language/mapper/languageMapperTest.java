package com.example.imple.language.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.transaction.annotation.Transactional;

import com.example.imple.dept.model.Dept;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class languageMapperTest {
	
	@Autowired
	LanguageMapper languageMapper;
	
	@Test
	void selectAll() {
		languageMapper.selectAll();
		

		
	}
	

}
