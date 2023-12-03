package com.example.imple.city.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.imple.city.model.City;
import com.example.imple.country.model.Country;
import com.example.imple.dept.model.Dept;
import com.github.pagehelper.Page;



@Mapper
public interface CityMapper {

	int 	   countAll();
	Country	   selectCountry();
	List<City> selectAll();
	List<City> selectAllWithCountry();
	Page<City> selectPage();
	Page<City> selectPageWithCountry();
	City 	   selectById(int id);
	City 	   selectByIdWithCountry(int id);
	List<City> selectByCountryCode(String countryCode);
	
	int insertCity(City city);
	int updateCity(City city);
	int deleteCity(City city);
	int delete(long id);
//	@Insert("""
//			insert into city
//			values
//			(#{id},
//			 #{name},
//			 #{countryCode, jdbcType=VARCHAR},
//			 #{district, jdbcType=VARCHAR},
//			 #{population})
//			""")
//	int insert(@Param("id") int id,
//			   @Param("name")  String name,
//			   @Param("countryCode") String countryCode,
//			   @Param("district") String district,
//			   @Param("population")  Integer population
//			   );  
//	
//	@Insert("""
//			insert into city
//			values
//			(#{c.id},
//			 #{c.name},
//			 #{c.countryCode, jdbcType=VARCHAR},
//			 #{c.district, jdbcType=VARCHAR},
//			 #{c.population})
//			""")
//	int insertCity(@Param("c") City city);
//
//	@Update("""
//		update city
//		   set population = #{population}
//		 where id= #{id}
//		""")
//	int updateCityPopulationById(@Param("id") int id,
//			   					 @Param("population")  Integer population
//	);
//	
//	@Update("""
//			update city
//			   set name         = #{c.name},
//			 	   country_code = #{c.countryCode, jdbcType=VARCHAR},
//			 	   district     = #{c.district, jdbcType=VARCHAR},
//			       population   = #{c.population}
//			 where id  			= #{c.id}
//			""")
//	int updateCity(@Param("c") City city);
//	
//	@Delete("delete from city where id=#{id}")
//	int delete(int id);
}
