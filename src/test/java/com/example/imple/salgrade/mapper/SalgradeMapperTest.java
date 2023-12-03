package com.example.imple.salgrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.imple.city.model.City;
import com.example.imple.country.model.Country;
import com.example.imple.dept.model.Dept;
import com.example.imple.emp.model.Emp;
import com.example.imple.salgrade.model.Salgrade;
import com.github.pagehelper.Page;

@SpringBootTest
public interface SalgradeMapperTest {

	
	@Select("select count(*) from salgrade")
	int countAll();
	
	@Select("select * from salgrade")
	List<Salgrade> selectAll();
	
	@Select("""
			select *
			  from grade
			 where  grade= #{grade} 
			""")
	Salgrade selectBygrade(int grade);
	
	@Insert("""
			insert into salgrade
			values
			(#{grade}, #{losal},#{hisal})
			""")
	int insert(@Param("grade") int grade, 
			   @Param("losal") int losal, 
			   @Param("hisal") int hisal);
	

}
