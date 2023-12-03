package com.example.imple.dept.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.dept.mapper.DeptMapper;
import com.example.imple.dept.model.DeptDTO;
import com.example.standard.controller.UpdateController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dept")
@Slf4j
public class DeptUpdateController implements UpdateController<DeptDTO>{
	
	@Autowired
	DeptMapper mapper;
	
	@Override
	public void update(Model model, HttpServletRequest request) { //create와 다르게 update는 deptno의 정보가 필요하다.
		log.info("Get update()");
		var error = request.getParameter("error");
		if (Objects.isNull(error)) {
			var session = request.getSession();
			session.removeAttribute("dept");
			session.removeAttribute("binding");
		}
		var deptno = request.getParameter("deptno");
		if(Objects.nonNull(deptno)) {
			var key = Integer.parseInt(deptno); //바꿀 때 에러날 수 있음->처리해야 함
			var dept = mapper.selectByDeptno(key);
			model.addAttribute("dept", dept);
		}
	}

	@Override
	@Transactional
	public String update(@Valid DeptDTO dto, BindingResult binding, Model model,  HttpServletRequest request, RedirectAttributes attr) {
		log.info("POST update()");
		System.out.println(dto);
		
		var session = request.getSession();
		session.setAttribute("dept", dto);
		session.setAttribute("binding", binding);
		
		if(binding.hasErrors())
			return "redirect:/dept/update?error";
		
		var dept = dto.getModel();
		try {
			mapper.updateDept(dept);
		} catch (DataIntegrityViolationException e) {
			binding.rejectValue("deptno", "9999", "없는 부서번호 입니다."); //rejectValue는 코드 줘야함
			return "redirect:/dept/update?error";
		}
		
		return "redirect:/dept/success?update";
	}

}
