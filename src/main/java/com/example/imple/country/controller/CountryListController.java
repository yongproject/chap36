package com.example.imple.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.imple.country.mapper.CountryMapper;
import com.example.imple.country.model.Country;
import com.example.standard.controller.PageableController;
import com.example.standard.controller.ListController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/country")
public class CountryListController implements ListController, PageableController{
	
	@Autowired
	CountryMapper mapper;
	
	@Override
	public void list(Model model, HttpServletRequest request) {
		log.trace("list(Model model) called");
		var list = mapper.selectAllWithCitys();
		model.addAttribute("list", list);
		list.forEach(e -> {
			log.debug(e.toString());
		});
	}

	@Override
	public String page(int pageNum, int pageSize, Model model) {
		log.trace("String page(int pageNum, int pageSize, Model model) called");
		PageHelper.startPage(pageNum, pageSize);
		Page<Country> list = mapper.selectPageWithCitys(); //Page는 arrayList를 상속받음-> 결국 List다
		var paging = PageInfo.of(list, 10);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		log.debug(paging.toString());
		
		return "country/page";
	}

}
