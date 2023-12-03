package com.example.imple.city.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.example.imple.city.model.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@SpringBootTest
public class CityMapperTest {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	CityMapper cityMapper;
	
	@Test
	void countAll() {
		var cnt = cityMapper.countAll();
		assertThat(cnt).isEqualTo(4079);
	}
	
	@Test
	void selectAll() {
		var list = cityMapper.selectAll();
		assertThat(list.size()).isEqualTo(4079);
	}
	
	@Test
	void selectAllWithCountry() {
		var list = cityMapper.selectAllWithCountry();
		assertThat(list.size()).isEqualTo(4079);
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getCountry()).isNotNull();
		});
	}
	
	@Test
	void selectPage() throws IOException {
		PageHelper.startPage(50, 5);
		Page<City> p = cityMapper.selectPage();
		PageInfo<City> paging = PageInfo.of(p);
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(paging);
	}
	
	@Test
	void selectPageWithCountry() throws IOException {
		PageHelper.startPage(1000, 5);
		Page<City> list = cityMapper.selectPageWithCountry();
//		assertThat(list.size()).isEqualTo(5);
		
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getCountry()).isNotNull();
		});
//		PageInfo<City> paging = PageInfo.of(list);
		var paging = PageInfo.of(list, 10);
		objectMapper.createGenerator(System.out)
		.writeObject(paging);
		
//		assertThat(paging).satisfies(e -> {
//			assertThat(e.getTotal()).isEqualTo(4079);
//			assertThat(e.getList().size()).isEqualTo(5);
//			long pages = e.getTotal()/5 + (e.getTotal()%5!=0 ? 1 : 0);
//			assertThat(e.getPages()).isEqualTo(pages);
//		});
		
	
	}
	
	@Test
	void selectByIdWithCountry() throws IOException {
		var city = cityMapper.selectByIdWithCountry(10);
		assertEquals(10, city.getId());
		
		objectMapper.createGenerator(System.out)
					.writeObject(city);
	}
	
	@Test
	@Transactional
	void insertCity() {
		var city = City.builder()
					   .name("xxx")
					   .build();
		cityMapper.insertCity(city);
		System.out.println(city);
		assertThat(city.getId()).isNotNull();
		
		//foreign key test
		assertThrows(DataIntegrityViolationException.class, ()->{
			var c = City.builder()
					    .name("서울")
					    .countryCode("xxx")
					    .build();
			cityMapper.insertCity(c);			
		});
		
		var c = City.builder()
					.name("서울")
					.countryCode("KOR")
					.build();
		cityMapper.insertCity(c);
		System.out.println(c);
	}
	
	@Test
	@Transactional
	void updateCity() {
		var seoul = cityMapper.selectById(2331);
		System.out.println(seoul);
		seoul.setName("서울");
		cityMapper.updateCity(seoul);
		System.out.println(seoul);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			seoul.setCountryCode("kor");
			cityMapper.updateCity(seoul);
		});
	}
	
	@Test
	@Transactional
	void deleteCity() {
		var cnt = cityMapper.delete(2331);
		assertThat(cnt).isEqualTo(1);
		
		var city = cityMapper.selectById(2331);
		assertThat(city).isNull();
		
		cnt = cityMapper.delete(900000);
		assertThat(cnt).isEqualTo(0);
	}
	
//	@Transactional
//	@Test
//	void insertCity() throws IOException {
//		var city = new City();
//		city.setId(4080L);
//		city.setName("ABC");
//		city.setPopulation(100000l);
//		cityMapper.insertCity(city);
//		
//		var result = cityMapper.selectById(4080);
//		assertThat(result).isEqualTo(city);
//		
//		objectMapper.createGenerator(System.out)
//		.useDefaultPrettyPrinter()
//		.writeObject(result);
//		
//		city = new City();
//		city.setId(4081L);
//		city.setPopulation(1000000l);
//		try {
//			cityMapper.insertCity(city);			
//		} catch (MyBatisSystemException e) {
//			System.out.println("name은 null이 될 수 없습니다.");
//		}
//	}
//	
//	@Transactional
//	@Test
//	void updateCityPopulationById() {
//		var cnt = cityMapper.updateCityPopulationById(4079, 100000);
//		assertThat(cnt).isEqualTo(1);
//		
//		var updatedPopulation = cityMapper.selectById(4079).getPopulation();
//		assertThat(updatedPopulation).isEqualTo(100000);
//		
//	}
//	
//	@Transactional
//	@Test
//	void updateCity() throws IOException {
//		var city = new City();
//		city.setId(4079L);
//		city.setName("ABC");
//		city.setCountryCode("KOR");
//		city.setDistrict("bb");
//		city.setPopulation(100000l);
//		var cnt = cityMapper.updateCity(city);
//		assertThat(cnt).isEqualTo(1);
//		
//		city = cityMapper.selectById(4079);
//		objectMapper.createGenerator(System.out)
//					.useDefaultPrettyPrinter()
//					.writeObject(city);
//	}
//	
//	@Transactional
//	@Test
//	void delete() {
//		var cnt = cityMapper.delete(4079);
//		assertThat(cnt).isEqualTo(1);
//		
//		assertThat(cityMapper.selectById(4079)).isNull();
//	}
	
}
