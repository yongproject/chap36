package com.example.imple.dept.model;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.standard.model.Modelable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class DeptDTO implements Modelable<Dept>{
	@NotNull// not null은 null만 신경씀
	@Range(min = 10, max = 99) //수치의 범위를 지정할 때 사용하는 것이 @Range
	Integer deptno; 
	
	
	@NotBlank //null,"","    "
	@Length(min=1, max = 14) //길이 제한 => @Length 
	String dname;
	
	@Length(min=0, max = 13)
	String loc; 
	
	@Override
	public Dept getModel() {
		return Dept.builder()
				   .deptno(deptno)
				   .dname(dname.trim()) //앞뒤에 space있으면 제거
				   .loc(loc.trim())
				   .build();
	}
}
