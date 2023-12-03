package com.example.imple.language.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.imple.language.model.Language;
import com.github.pagehelper.Page;

@Mapper
public interface LanguageMapper {
	@Select("select * from country_language")
	List<Language> selectAll();
	
	@Select("""
			select *
			  from country_language
			 where country_code = #{countryCode} and
			 	   language     = #{language}
			""")
	Language selectByCountryCodeAndLanguage(@Param("countryCode") String countryCode,
											@Param("language") String language);
	
	@Select("select * from country_language")
	Page<Language> selectPage();
	
	@Insert("""
			insert into country_language
			values
			(#{countryCode}, #{language}, #{isOfficial}, #{percentage})
			""")
	int insertLanguage(Language language);
}
