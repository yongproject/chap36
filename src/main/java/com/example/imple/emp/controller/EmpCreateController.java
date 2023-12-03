package com.example.imple.emp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.emp.mapper.EmpMapper;
import com.example.imple.emp.model.EmpDTO;
import com.example.standard.controller.CreateController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpCreateController implements CreateController<EmpDTO>{
	
	@Autowired
	EmpMapper mapper;
	
	@Override
	public void create(Model model, HttpServletRequest request) {
		var error = request.getParameter("error"); //값을 넘겨주지 않으면 빈 String
		if(Objects.isNull(error)) {
			var session = request.getSession();
			session.removeAttribute("emp");
			session.removeAttribute("binding");
		}
	}

	@Override
	@Transactional
	public String create(EmpDTO dto, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes attr) {
		var session = request.getSession();
		session.setAttribute("emp", dto);
		session.setAttribute("binding", binding);
		
		if(binding.hasErrors()) { //에러가 하나라도 있으면 실행됨
			return "redirect:/emp/create?error";
		}
		
		var emp = dto.getModel();
		try {
			mapper.insertEmp(emp);
		} catch (DuplicateKeyException e) {
			binding.reject("duplicate key","직원번호가 중복됩니다."); //글로벌 에러. code명, 메시지 내용, rejectValue()는 필드 에러
			return "redirect:/emp/create?error";
		} catch (DataIntegrityViolationException e){
			binding.reject("foreign key", "해당 부서번호는 dept 테이블에 존재하지 않습니다.");
			return "redirect:/emp/create?error";
		}
		
		
		
		
		
		return "redirect:/emp/success?create";
	}

}
