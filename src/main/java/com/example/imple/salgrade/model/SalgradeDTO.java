package com.example.imple.salgrade.model;

import com.example.imple.dept.model.Dept;
import com.example.standard.model.Modelable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.Model;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalgradeDTO implements Modelable<Salgrade> {
	
	@NotNull
	int 		grade;
	
	Integer 	losal;
	
	Integer 	hisal;
	
	@Override
	public Salgrade getModel() {
		return Salgrade.builder()
				   .grade(grade)
				   .losal(losal)
				   .hisal(hisal)
				   .build();
	}
}
