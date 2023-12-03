package com.example.imple.language.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.country.mapper.CountryMapper;
import com.example.imple.country.model.CountryDTO;
import com.example.imple.language.model.LanguageDTO;
import com.example.standard.controller.DeleteController;
import com.example.standard.controller.UpdateController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/language")
public class LanguageDeleteController implements DeleteController<LanguageDTO> {
	
	@Autowired
	CountryMapper mapper;
	
	@Override
	public void delete(Model model, HttpServletRequest request) {
		var error = request.getParameter("error");
		if(Objects.isNull(error)){
			var session = request.getSession();
			session.removeAttribute("city");
			session.removeAttribute("binding");
		}
		
		var code = request.getParameter("code");
		if(Objects.nonNull(code)) {
			var language = mapper.selectByCode(code);
			model.addAttribute("language", language);
		}
	}

	@Override
	public String delete(@Valid LanguageDTO dto, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes attr) {
		
		var session = request.getSession();
		session.setAttribute("language", dto);
		session.setAttribute("binding", binding);
		
		var code = dto.getCountryCode().trim();
		
		if(!code.equals("")) 
			if(code.length() != 3) {
				binding.rejectValue("countryCode", "9999", "3자리로 입력하세요.");
			}
		
		if(binding.hasErrors()) {
			return "redirect:/language/delete?error";
		}
		
		var language = dto.getModel();
		mapper.delete(language.getCountryCode());			
		
		return "redirect:/language/success?delete";
	}

}
