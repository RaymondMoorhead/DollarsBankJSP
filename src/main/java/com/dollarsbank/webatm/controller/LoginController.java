package com.dollarsbank.webatm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dollarsbank.webatm.model.Account;
import com.dollarsbank.webatm.service.AccountService;

@Controller
public class LoginController {
	@Autowired
	AccountService service;
	@Autowired
    private Account curAccount;
	
    @ModelAttribute("curUser")
    public Account getCurAccount() {
        return this.curAccount;
    }
	
	@GetMapping(value = "/login")
	public String showLoginPage(ModelMap model){
		model.put("maxUserIdLength", service.maxUserIdLength);
		model.put("maxPasswordLength", service.maxPasswordLength);
		model.put("highestMaxLength", service.highestMaxLength);
		curAccount.clear();
		return "login";
	}
	
	@PostMapping(value = "/login")
	public ModelAndView showMainAccountPage(ModelMap model, @RequestParam String userId, @RequestParam String password){
		
		Account account = service.getAccount(userId, password);
		
		if (account == null) {
			System.out.println("Login failed");
			model.put("errorMessage", "Invalid Credentials");
			return new ModelAndView("redirect:/login");
		}
		curAccount.copy(account);
		
		System.out.println("Login Succeeded, account is: " + account.toString());
		
		//return "list-user-characters";
		return new ModelAndView("redirect:/main-account-page");
	}
}
