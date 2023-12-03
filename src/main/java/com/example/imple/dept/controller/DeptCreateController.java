package com.example.imple.dept.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.dept.mapper.DeptMapper;
import com.example.imple.dept.model.DeptDTO;
import com.example.standard.controller.CreateController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dept")
@Slf4j
public class DeptCreateController implements CreateController<DeptDTO>{
	@Autowired
	DeptMapper mapper;
	
	@Override
	public void create(Model model, HttpServletRequest request) {
		log.info("Get create()");
		var error = request.getParameter("error");
		if (error == null) {
			var session = request.getSession();
			session.removeAttribute("dept");
			session.removeAttribute("binding");
		}
	}

	@Override
	@Transactional
	public String create(DeptDTO dto,
						 BindingResult binding,
						 Model model,
						 HttpServletRequest request,
			             RedirectAttributes attr) {
		log.info("POST create()");
		System.out.println(dto);
		
		var session = request.getSession();
		session.setAttribute("dept", dto);
		session.setAttribute("binding", binding);

		if(binding.hasErrors())
			return "redirect:/dept/create?error";
		
		var dept= dto.getModel();
		try {
			mapper.insertDept(dept);
		} catch (DuplicateKeyException e) { //global error 활용
			binding.reject("duplicate key","부서번호가 중복됩니다."); //code명, 메시지 내용
			return "redirect:/dept/create?error";
		}
		
		return "redirect:/dept/success?create"; //dml을 사용하면 반드시 redirect해야한다. 안하면 반복적으로 작업이 수행됨
	}
}
