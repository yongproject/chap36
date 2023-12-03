package com.example.imple.country.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.country.mapper.CountryMapper;
import com.example.imple.country.model.CountryDTO;
import com.example.standard.controller.UpdateController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/country")
public class CountryUpdateController implements UpdateController<CountryDTO> {
	
	@Autowired
	CountryMapper mapper;
	
	@Override
	public void update(Model model, HttpServletRequest request) {
		var error = request.getParameter("error");
		if(Objects.isNull(error)){
			var session = request.getSession();
			session.removeAttribute("city");
			session.removeAttribute("binding");
		}
		
		var code = request.getParameter("code");
		if(Objects.nonNull(code)) {
			var country = mapper.selectByCode(code);
			model.addAttribute("country", country);
		}
	}

	@Override
	public String update(@Valid CountryDTO dto, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes attr) {
		
		var session = request.getSession();
		session.setAttribute("country", dto);
		session.setAttribute("binding", binding);
		
		var code = dto.getCode().trim();
		
		if(!code.equals("")) //countryCode가 3자리거나 null이도록
			if(code.length() != 3) {
				binding.rejectValue("countryCode", "9999", "3자리로 입력하세요.");
			}
		
		if(binding.hasErrors()) {
			return "redirect:/country/update?error";
		}
		
		var country = dto.getModel();
		mapper.updateCountry(country);			
		
		return "redirect:/country/success?update";
	}

}
