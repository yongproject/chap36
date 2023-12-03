package com.example.imple.salgrade.controllor;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.dept.mapper.DeptMapper;
import com.example.imple.dept.model.DeptDTO;
import com.example.imple.emp.model.EmpDTO;
import com.example.imple.salgrade.mapper.SalgradeMapper;
import com.example.imple.salgrade.model.SalgradeDTO;
import com.example.standard.controller.DeleteController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/salgrade")
@Slf4j
public class SalgradeDeleteController implements DeleteController<SalgradeDTO> {

	@Autowired
	SalgradeMapper mapper;
	
	@Override
	public void delete(Model model, HttpServletRequest request) {
		var error = request.getParameter("error");
		if (Objects.isNull(error)) {
			var session = request.getSession();
			session.removeAttribute("salgrade");
			session.removeAttribute("binding");
		}
		
		var hisal = request.getParameter("hisal");
		if (Objects.nonNull(hisal)) {
			var key = Integer.parseInt(hisal);
			var salgrade = mapper.selectByGrade(key);
			model.addAttribute("salgrade", salgrade);
		}
			
		
	}

	@Override
	public String delete(@Valid SalgradeDTO dto, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes attr) {
		
		var session = request.getSession();
		session.setAttribute("salgrade", dto);
		session.setAttribute("binding", binding);
		
		if (binding.hasErrors())
			return "redirect:/salgrade/delete?error";
		
		var salgrade = dto.getModel();
		
		mapper.delete(salgrade.getHisal());
		
		return "redirect:/salgrade/success?delete";
	}

}