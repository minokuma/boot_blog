package com.blog.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.domain.User;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();
	
/*	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute(attributeName, attributeValue);
		return "list";
	}*/
	
	@GetMapping("/create")
	public String create(User user) {
		System.out.println("user : " + user.toString());
		users.add(user);
		System.out.println("create!");
		return "form";
	}
	
}
