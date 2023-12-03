package com.example.standard.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MappedTypes(Continent.class) //Continent 타입에 대해서 핸들링할 때 이걸 써라!(다른 맵핑이 필요할 때 Handler 사용)
public class ContinentTypeHandler implements TypeHandler<Continent> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Continent parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getSymbol()); //DB에 들어갈 때 symbol이 들어간다. "North America"
		log.trace(String.format("[%s => %s]", parameter, parameter.getSymbol())); //trace로 설정하면 보임
	}

	@Override
	public Continent getResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName); // column값이 null일 수가 있다.
		
		if(value != null) {
			Continent continent = Continent.valueOf(value.replace(" ", ""));
			log.trace(String.format("[%s <= %s]", continent, value)); 
			return continent;			
		}
		
		return null;
	}

	@Override
	public Continent getResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex); // column값이 null일 수가 있다.
		
		if(value != null) {
			Continent continent = Continent.valueOf(value.replace(" ", ""));
			log.trace(String.format("[%s <= %s]", continent, value)); 
			return continent;			
		}
		
		return null;
	}

	@Override
	public Continent getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex); // column값이 null일 수가 있다.
		
		if(value != null) {
			Continent continent = Continent.valueOf(value.replace(" ", ""));
			log.trace(String.format("[%s <= %s]", continent, value)); 
			return continent;			
		}
		
		return null;	
	}


}
