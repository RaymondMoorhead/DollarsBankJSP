package com.dollarsbank.webatm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dollarsbank.webatm.model.Account;
import com.dollarsbank.webatm.service.AccountService;

@Controller
public class LoginController {
	@Autowired
	AccountService service;
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session){
		session.removeAttribute("curAccount");
		return "forward:/login";
	}
	
	@PostMapping(value = "/logout")
	public ModelAndView callLoginPost(ModelMap model, HttpSession session,
											@RequestParam String username,
											@RequestParam String password){
		// logout has to forward to keep the model data, but doing so make logging
		// in after logging out call POST for logout, so this is a necessary band-aid
		return showMainAccountPage(model, session, username, password);
	}
	
	@GetMapping(value = "/login")
	public String showLoginPage(ModelMap model){
		model.put("maxUserIdLength", service.maxUsernameLength);
		model.put("maxPasswordLength", service.maxPasswordLength);
		model.put("highestMaxLength", Math.max(service.maxUsernameLength, service.maxPasswordLength));
		return "login";
	}
	
	@PostMapping(value = "/login")
	public ModelAndView showMainAccountPage(ModelMap model, HttpSession session,
											@RequestParam String username,
											@RequestParam String password){
		
		Account account = service.getAccount(username, password);
		
		if (account == null) {
			System.out.println("Login failed");
			model.put("errorMessage", "Invalid Credentials");
			return new ModelAndView("login");
		}
		session.setAttribute("curAccount", account);
		
		System.out.println("Login Succeeded, account is: " + account.toString());
		return new ModelAndView("redirect:/main-account-page");
	}
}
