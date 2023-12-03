package com.example.standard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface PageableController {
	
	@GetMapping("/page/{pageNum}/{pageSize}")
	String page(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, Model model);
}
