package com.example.imple.language.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.city.mapper.CityMapper;
import com.example.imple.city.model.CityDTO;
import com.example.imple.language.mapper.LanguageMapper;
import com.example.imple.language.model.LanguageDTO;
import com.example.standard.controller.CreateController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/language")
public class LanguageCreateController implements CreateController<LanguageDTO> {
	
	@Autowired
	LanguageMapper mapper;
	
	@Override
	public void create(Model model, HttpServletRequest request) {
		var error = request.getParameter("error");
		if(Objects.isNull(error)){
			var session = request.getSession();
			session.removeAttribute("language");
			session.removeAttribute("binding");
		}	
	}

	@Override
	public String create(@Valid LanguageDTO dto, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes attr) {
		
		var session = request.getSession();
		session.setAttribute("language", dto);
		session.setAttribute("binding", binding);
			
		if(binding.hasErrors()) {
			return "redirect:/language/create?error";
		}
		
		var language = dto.getModel();
		try {
			mapper.insertLanguage(language);			
		} catch (DuplicateKeyException e) {
			binding.reject("Primary Key", "해당 국가에 이미 존재하는 언어입니다.");
			return "redirect:/language/create?error";
		} catch (DataIntegrityViolationException e) {
			binding.reject("foreign", "입력한 국가코드는 존재하지 않습니다.");
			return "redirect:/language/create?error";
		} 		
		return "redirect:/language/success?create";
	}

}
