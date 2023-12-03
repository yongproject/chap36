package com.example.imple.salgrade.model;

import java.util.List;

import com.example.imple.dept.model.Dept;
import com.example.imple.emp.model.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salgrade {
	int grade;
	Integer losal;
	Integer hisal;
}
