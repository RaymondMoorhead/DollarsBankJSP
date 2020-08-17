package com.dollarsbank.webatm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dollarsbank.webatm.model.Account;
import com.dollarsbank.webatm.service.AccountService;
import com.dollarsbank.webatm.utility.TransactionUtility;

@Controller
public class RegisterController {
	@Autowired
	AccountService service;
	
	@GetMapping(value = "/register")
	public String showRegisterPage(ModelMap model){
		model.put("maxUserIdLength", service.maxUsernameLength);
		model.put("maxPasswordLength", service.maxPasswordLength);
		model.put("maxNameLength", service.maxNameLength);
		model.put("maxAddressLength", service.maxAddressLength);
		model.put("maxContactNumberLength", service.maxContactNumberLength);
		model.put("highestMaxLength", service.maxContactNumberLength);
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String showMainAccountPage(ModelMap model, HttpSession session,
										@RequestParam String username,
										@RequestParam String password,
										@RequestParam String name,
										@RequestParam String address,
										@RequestParam String contactNumber,
										@RequestParam String balance){
		
		long trueBalance;
		try {
			trueBalance = TransactionUtility.parseAmount(balance);
		} catch(Exception e) {
			model.put("errorMessage", "Invalid balance amount");
			return "register";
		}
		
		Account account = service.createAccount(username, password, name, address, contactNumber, trueBalance);
		
		if (account == null) {
			model.put("errorMessage", service.getError());
			return "register";
		}
		
		session.setAttribute("curAccount", account);
		
		System.out.println("Registration Succeeded, account is: " + account.toString());
		
		return "redirect:/main-account-page";
	}
}
