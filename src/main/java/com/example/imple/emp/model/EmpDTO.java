package com.example.imple.emp.model;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.standard.model.Modelable;
import com.example.standard.util.Gender;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class EmpDTO implements Modelable<Emp> {
	@NotNull
	@Range(min = 1000, max = 9999)
	Integer   empno;
	
	@NotBlank
	@Length(max = 10)
	String 	  ename;
	
	@Pattern(regexp = "M|F|{0}", message = "M | F | null만 허용됩니다.")
	String 	  gender;
	
	@Length(max = 9)
	String 	  job;
	
	@Range(min = 1000, max = 9999)
	Integer   mgr;
	
	@PastOrPresent //과거 또는 현재만 가능
	LocalDate hiredate;
	
	@Digits(integer = 5, fraction = 2) // Digits: 실수 처리
	Double 	  sal; //NUMBER(7,2) 총 7자리, 소수부 2자리까지 가능
	
	@Digits(integer = 5, fraction = 2)
	Double 	  comm;
	
	@Range(min = 10, max = 99)
	Integer   deptno;
	
	@Override
	public Emp getModel() {
		//3항연산자 안쓰고 조건 처리
//		Gender gender = null;
//		if(!this.gender.equals(""))
//			gender = Gender.valueOf(this.gender);
		
		
		return Emp.builder()
				  .empno(empno)
				  .ename(ename.trim())
				  .gender(gender=="" ? null : Gender.valueOf(gender))
				  .job(job.trim())
				  .mgr(mgr)
				  .hiredate(hiredate)
				  .sal(sal)
				  .comm(comm)
				  .deptno(deptno)
				  .build();
	}
}
