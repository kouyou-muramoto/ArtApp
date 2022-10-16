package com.example.demo.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.app.user.entity.User;
import com.example.demo.app.user.service.UserService;

@Controller
public class UserController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {

		return "html/login";
	}
	
	@GetMapping("/kaninnyou")
	public String kaninnyou(@AuthenticationPrincipal User currentUser,
			Model model) {
		model.addAttribute("currentUserId",currentUser.getId());
		return "html/kaninnyou";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("userRegistrationForm", new UserRegistrationForm());
		return "html/signup";
	}
	
	@PostMapping("/registration")
	public String resistration(@Validated @ModelAttribute UserRegistrationForm userRegisetraionForm,
								BindingResult result,
								Model model
								) 
	{
		if(result.hasErrors()) {
			return "html/signup";
		}
		
	    User user =new User();
		user.setEmail(userRegisetraionForm.getEmail());
		user.setName(userRegisetraionForm.getName());
		user.setUsername(userRegisetraionForm.getLoginId());
		user.setPassword(passwordEncoder.encode(userRegisetraionForm.getPassword()));
		user.setPasswordConfirm(passwordEncoder.encode(userRegisetraionForm.getPasswordConfirm()));
		
		userService.registerUser(user);
		
		return "redirect:/login";
		
	}
	
	@PostMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Integer id) {
		
		userService.deleteUser(id.toString());
		return "redirect:/login";
	}
	
	
}
