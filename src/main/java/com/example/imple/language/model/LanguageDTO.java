package com.example.imple.language.model;

import org.hibernate.validator.constraints.Length;

import com.example.standard.model.Modelable;
import com.example.standard.util.IsOfficial;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDTO implements Modelable<Language>{
	@NotBlank
	@Length(min = 3, max = 3, message = "countryCode는 3자리입니다.")
	String countryCode;
	
	@NotBlank
	@Length(max = 30)
	String language;
	
	@Pattern(regexp = "T|F|{0}")
	String isOfficial;
	
	@Digits(integer = 4, fraction = 1)
	Double percentage;

	@Override
	public Language getModel() {
		return Language.builder()
					   .countryCode(countryCode)
					   .language(language)
					   .isOfficial(IsOfficial.valueOf(isOfficial))
					   .percentage(percentage)
					   .build();
	}
	
}
