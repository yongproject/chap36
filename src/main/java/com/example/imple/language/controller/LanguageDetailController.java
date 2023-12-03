package com.example.imple.language.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.imple.language.mapper.LanguageMapper;
import com.example.standard.controller.DetailController;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/language")
public class LanguageDetailController implements DetailController<String> {
	
	@Autowired
	LanguageMapper mapper;
	
	@Override
	public String detail(String key, Model model, HttpServletRequest request) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Implemenation");
	}
	
	@Override
	public String detail(String key1, String key2, Model model, HttpServletRequest request) {
		var language = mapper.selectByCountryCodeAndLanguage(key1, key2);
		model.addAttribute("language", language);
		return "language/detail";
	}

}
