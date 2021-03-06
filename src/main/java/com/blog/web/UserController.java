	package com.blog.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.domain.User;
import com.blog.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		
		if(user == null) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		
		if (!password.equals(user.getPassword())) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm"; 
		}
		
		System.out.println("Login Success!");
		session.setAttribute("username", user);
		
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/users/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		
		Object user_ = session.getAttribute("username");
		if(user_ == null) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = (User) user_;
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("you can only edit your own information.");
		}
		
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/user/update_form";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser, HttpSession session) {

		Object user_ = session.getAttribute("username");
		if(user_ == null) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = (User) user_;
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("you can only edit your own information.");
		}
		
		userRepository.save(newUser);
		return "redirect:/users/list";
	}
	
}
