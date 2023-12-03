package com.example.imple.country.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.standard.model.Modelable;
import com.example.standard.util.Continent;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class CountryDTO implements Modelable<Country> {
	 @NotBlank
	 String  	code;
	 
	 @NotBlank
	 @Length(max = 52)
	 String  	name;
	 
	 @Length(max = 20)
	 @Pattern(regexp = "Asia|Europe|North America|Africa|Oceania|Antarctica|South America|{0}",
	 		  message = "다음의 값만 가질 수 있습니다. 'Asia','Europe','North America','Africa','Oceania','Antarctica','South America'")
	 String  continent;
	 
	 @Length(max = 26)
	 String 	region;
	 
	 @Digits(integer = 10, fraction = 2)
	 Double  	surfaceArea;
	 
	 @Range(min = 1, max = 999999)
	 Integer 	indepYear;
	 
	 @Range(min = 1, max = 99999999999l)
	 Long 	 	population;
	 
	 @Digits(integer = 3, fraction = 1)
	 Double  	lifeExpectancy;
	 
	 @Digits(integer = 10, fraction = 2)
	 Double  	gnp;
	 
	 @Digits(integer = 10, fraction = 2)
	 Double  	gnpOld;
	 
	 @Length(max = 45)
	 String  	localName;
	 
	 @Length(max = 45)
	 String  	governmentForm;
	 
	 @Length(max = 60)
	 String  	headOfState;
	 
	 @Range(min = 1, max = 99999999999l)
	 Long    	capital;
	 
	 String  	code2;
	
	@Override
	public Country getModel() {
		Continent continent = null;
		if(!this.continent.equals(""))
			continent = Continent.valueOf(this.continent.replace(" ", ""));
		return Country.builder()
				   .code(code)
				   .name(name)
				   .continent(continent)
				   .region(region)
				   .surfaceArea(surfaceArea)
				   .indepYear(indepYear)
				   .population(population)
				   .lifeExpectancy(lifeExpectancy)
				   .gnp(gnp)
				   .gnpOld(gnpOld)
				   .localName(localName)
				   .governmentForm(governmentForm)
				   .headOfState(headOfState)
				   .capital(capital)
				   .code2(code2)
				   .build();
	}
}
