package com.example.imple.salgrade.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.imple.emp.mapper.EmpMapper;
import com.example.imple.salgrade.mapper.SalgradeMapper;
import com.example.standard.controller.ListController;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/salgrade")
public class SalgradeListController implements ListController {
	
	@Autowired
	SalgradeMapper mapper;
	
	@Override
	public void list(Model model, HttpServletRequest request) {
		var list = mapper.selectAll();
		model.addAttribute("list", list);
	}

}
