package com.example.imple.emp.model;

import java.time.LocalDate;

import com.example.imple.dept.model.Dept;
import com.example.standard.util.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class Emp {
	@NonNull Integer 	  empno;
	@NonNull String 	  ename;
			 Gender 	  gender;
			 String 	  job;
			 Integer   	  mgr;
			 LocalDate 	  hiredate; //날짜만 저장(시간x)
			 Double 	  sal;
			 Double 	  comm;
			 Integer   	  deptno;
			 Dept		  dept; //dept와 1:1관계
}
