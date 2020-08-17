package com.dollarsbank.webatm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dollarsbank.webatm.model.Account;
import com.dollarsbank.webatm.service.AccountService;
import com.dollarsbank.webatm.utility.TransactionUtility;

@Controller
public class MainPageController {
	@Autowired
	AccountService service;
	@Autowired
    private Account curAccount;
	
    @ModelAttribute("curUser")
    public Account getCurAccount() {
        return this.curAccount;
    }
    
    private void setCurAccount(HttpSession session) {
    	curAccount.copy((Account) session.getAttribute("curAccount"));
    }
    
    private boolean updateBalance(boolean add, String balance, Account account, String message) {
		long trueBalance;
		try {
			trueBalance = TransactionUtility.parseAmount(balance);
		} catch(Exception e) {
			return false;
		}
		if(add)
			account.addAmount(trueBalance, message);
		else
			account.subtractAmount(trueBalance, message);
		service.updateAccount(account);
		return true;
    }
	
	@GetMapping(value = "/main-account-page")
	public String showMainPage(HttpSession session){
		setCurAccount(session);
		return "main-account-page";
	}
	
	@GetMapping(value = "/account-details")
	public String showAccountDetailsPage(HttpSession session){
		setCurAccount(session);
		return "account-details";
	}
	
	@GetMapping(value = "/deposit")
	public String showDepositPage(HttpSession session){
		setCurAccount(session);
		return "deposit";
	}
	
	@PostMapping(value = "/deposit")
	public String performDeposit(HttpSession session,
									ModelMap model,
									@RequestParam String balance){
		
		setCurAccount(session);
		if(updateBalance(true, balance, curAccount, "Local Deposit"))
			model.put("successMessage", "Deposit Successful");
		else
			model.put("errorMessage", "Invalid balance amount");
		return "deposit";
	}
	
	@GetMapping(value = "/withdraw")
	public String showWithdrawPage(HttpSession session){
		setCurAccount(session);
		return "withdraw";
	}
	
	@PostMapping(value = "/withdraw")
	public String performWithdraw(HttpSession session,
									ModelMap model,
									@RequestParam String balance){
		
		setCurAccount(session);
		if(updateBalance(false, balance, curAccount, "Local Withdrawl"))
			model.put("successMessage", "Withdrawl Successful");
		else
			model.put("errorMessage", "Invalid balance amount");
		return "withdraw";
	}

	@GetMapping(value = "/transfer")
	public String showTransferPage(HttpSession session){
		setCurAccount(session);
		return "transfer";
	}
	
	@GetMapping(value = "/recent-transactions")
	public String showRecentTransactionsPage(HttpSession session){
		setCurAccount(session);
		return "recent-transactions";
	}
}
