package com.example.imple.dept.mapper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.imple.dept.mapper.DeptMapper;
import com.example.imple.dept.model.Dept;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class DeptMapperTest {
	
	@Autowired
	DeptMapper deptMapper; // 의존성 주입(DI, Dependency Injection)
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void selectEmps() throws IOException {
		var list = deptMapper.selectEmps(10);
		objectMapper.createGenerator(System.out)
					.writeObject(list);
	}
	
	@Test
	void selectAll() throws IOException {
		var list = deptMapper.selectAll();
		System.out.println(list);
//		objectMapper.createGenerator(System.out)
//					.writeObject(list);
		assertThat(list).allMatch(e -> {
			return e.getEmps() == null;
		});
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getEmps()).isNull();
		});
	}
	
	@Test
	void selectAllWithEmps() throws IOException {
		var list = deptMapper.selectAllWithEmps();
		System.out.println(list);
		objectMapper.createGenerator(System.out)
					.writeObject(list);
		assertThat(list).allMatch(e -> {
			return e.getEmps() != null;
		});
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getEmps()).isNotNull();
			assertThat(e.getEmps().size()).isGreaterThanOrEqualTo(0);
		});
	}
	
	@Test
	void selectByDeptno() {
		var dept = deptMapper.selectByDeptno(10);
		assertThat(dept.getDeptno()).isEqualTo(10);
		
		dept = deptMapper.selectByDeptno(90);
		assertNull(dept);
	}
	
	@Test
	void selectByDeptnoWithEmps() {
		var dept = deptMapper.selectByDeptnoWithEmps(10);
		assertThat(dept.getDeptno()).isEqualTo(10);
		assertThat(dept.getEmps()).isNotNull();
		
		dept = deptMapper.selectByDeptnoWithEmps(90);
		assertNull(dept);
		
	}
	
//	@Rollback(false)
	@Test
	@Transactional //Test Code에서는 무조건 Rollback함
	void insert() {
		deptMapper.insert(50, "개발부", "부산");
		System.out.println(deptMapper.selectByDeptno(50));
		
		deptMapper.insert(60, "개발2부", null);
		System.out.println(deptMapper.selectByDeptno(60));
		
		try {
			deptMapper.insert(50, "개발3부", "서울");			
		} catch(DuplicateKeyException e) {
			System.out.println("50번 부서는 사용할 수 없습니다.");
		}
		
		try {
			deptMapper.insert(70, null, null);
		} catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
		}
		
		//에러가 날 것을 단정한다!(에러가 나면 true)
		assertThrows(DataIntegrityViolationException.class, () -> {
			deptMapper.insert(100, "총무부", null);
		});
		
	}
	
	@Test
	@Transactional
	void insertDept() throws IOException{
		var dept = Dept.of(50, "개발1부", "서울", null);
		int cnt = deptMapper.insertDept(dept);
		assertThat(cnt).isEqualTo(1);
		
		dept = Dept.of(60, "개발2부", null, null);
		cnt = deptMapper.insertDept(dept);
		assertThat(cnt).isEqualTo(1);
		
		assertThrows(DuplicateKeyException.class, () -> {
			deptMapper.insertDept(Dept.of(60, "개발3부", null, null));
		});
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			deptMapper.insertDept(Dept.of(60, null, null, null));			
		});
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			deptMapper.insertDept(Dept.of(200, "개발4부", null, null));			
		});
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(deptMapper.selectAll());
	}
	
	@Test
	@Transactional
	void update() throws IOException {
		int cnt = deptMapper.update(10, "xxx", "yyy");
		assertThat(cnt).isEqualTo(1);
		
		cnt = deptMapper.update(50, "xxx", "yyy");
		assertThat(cnt).isEqualTo(0);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			try {
				deptMapper.update(20, null, "서울");
			} 
			catch (Exception e) {
				throw new DataIntegrityViolationException(e.getMessage());
			}
		});
		
		cnt = deptMapper.update(100, "개발부", "부산");
		assertThat(cnt).isEqualTo(0);
		
		cnt = deptMapper.update(100, "개발4부", null);
		assertThat(cnt).isEqualTo(0);
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(deptMapper.selectAll());
	}
	
	
	@Test
	@Transactional
	void updateDept() {
		var dept = Dept.of(10,"xxx", "yyy", null);
		int cnt = deptMapper.updateDept(dept);
		assertThat(cnt).isEqualTo(1);
	}
	
	@Test
	@Transactional
	void delete() throws IOException {
		int cnt = deptMapper.delete(90);
		assertThat(cnt).isEqualTo(0);
		
		cnt = deptMapper.delete(40);
		assertThat(cnt).isEqualTo(1);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			deptMapper.delete(10);			
		});
		
		objectMapper.createGenerator(System.out)
		.useDefaultPrettyPrinter()
		.writeObject(deptMapper.selectAll());
	}
	
	
}
