package com.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("")
	public String home(Model model) {
		// TODO Auto-generated method stub
		//model.addAttribute("questions", questionRepository.findAll());
		return "index";
	}

}
