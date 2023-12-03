package com.example.imple.language.model;

import com.example.standard.util.IsOfficial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Language {
	@NonNull   String countryCode;
	@NonNull   String language;
	IsOfficial isOfficial;
	Double     percentage;
}
