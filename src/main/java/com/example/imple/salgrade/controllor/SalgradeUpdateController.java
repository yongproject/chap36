package com.example.imple.salgrade.controllor;

import java.util.Objects;

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
import com.example.imple.salgrade.mapper.SalgradeMapper;
import com.example.imple.salgrade.model.SalgradeDTO;
import com.example.standard.controller.CreateController;
import com.example.standard.controller.DetailController;
import com.example.standard.controller.UpdateController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/salgrade")
@Slf4j
public class SalgradeUpdateController implements UpdateController<SalgradeDTO> {

	@Autowired
	SalgradeMapper mapper;

	@Override
	public void update(Model model, HttpServletRequest request) {
		log.info("GET update()...");
		var error = request.getParameter("error");
		if (Objects.isNull(error)) {
			var session = request.getSession();
			session.removeAttribute("dept");
			session.removeAttribute("binding");
		}
		
		var deptno = request.getParameter("deptno");
		if (Objects.nonNull(deptno)) {
			var key = Integer.parseInt(deptno);
			var salgrade = mapper.selectByGrade(key);
			model.addAttribute("salgrade", salgrade);
		}
	}

	@Override
	@Transactional
	public String update(@Valid SalgradeDTO dto, BindingResult binding, Model model, HttpServletRequest request, RedirectAttributes attr) {
		log.info("POST update()...");
		
		var session = request.getSession();
		session.setAttribute("salgrade", dto);
		session.setAttribute("binding", binding);
		
		if (binding.hasErrors())
			return "redirect:/salgrade/update?error";
		
		var salgrade = dto.getModel();
		
		mapper.updateSalgrade(salgrade);
		
		
		return "redirect:/salgrade/success?update";
	}
	
}
