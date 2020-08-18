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

import com.dollarsbank.webatm.model.Account;
import com.dollarsbank.webatm.service.AccountService;
import com.dollarsbank.webatm.utility.TransactionUtility;

@Controller
public class MainPageController {
	@Autowired
	AccountService service;

    private Account curAccount;
	
    @ModelAttribute("curUser")
    public Account getCurAccount() {
        return this.curAccount;
    }
    
    private void setCurAccount(HttpSession session) {
    	curAccount = (Account) session.getAttribute("curAccount");
    }
    
    private boolean updateBalance(boolean add, String balance, Account account, String message) {
		long trueBalance;
		try {
			trueBalance = TransactionUtility.parseAmount(balance);
		} catch(Exception e) {
			return false;
		}
		if(add) {
			if(!account.addAmount(trueBalance, message))
				return false;
		}
		else {
			if(!account.subtractAmount(trueBalance, message))
				return false;
		}
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
	
	@GetMapping(value = "/delete-account")
	public String deleteAccount(HttpSession session, ModelMap model){
		setCurAccount(session);
		String goodbye = "Thank you for banking with us";
		if(curAccount.getBalance() > 0)
			goodbye = goodbye +
						", a check for your remaining balance (" +
						TransactionUtility.parseAmount(curAccount.getBalance()) +
						") will be sent to " + curAccount.getAddress();
		model.put("successMessage", goodbye);
		service.deleteAccount(curAccount.getUsername(), curAccount.getPassword());
		return "forward:/logout";
	}
	
	@GetMapping(value = "/deposit")
	public String showDepositPage(HttpSession session){
		setCurAccount(session);
		return "deposit";
	}
	
	@PostMapping(value = "/deposit")
	public ModelAndView performDeposit(HttpSession session,
									ModelMap model,
									@RequestParam String balance,
									@RequestParam String memo){

		setCurAccount(session);
		if(updateBalance(true, balance, curAccount, "Local Deposit" + (memo.isEmpty() ? "" : ": " + memo))) {
			model.put("successMessage", "Deposit Successful");
			return new ModelAndView("main-account-page");
		}
		else {
			model.put("errorMessage", "Invalid balance amount");
			return new ModelAndView("deposit");
		}
	}
	
	@GetMapping(value = "/withdraw")
	public String showWithdrawPage(HttpSession session){
		setCurAccount(session);
		return "withdraw";
	}
	
	@PostMapping(value = "/withdraw")
	public ModelAndView performWithdraw(HttpSession session,
									ModelMap model,
									@RequestParam String balance,
									@RequestParam String memo){
		
		setCurAccount(session);
		if(updateBalance(false, balance, curAccount, "Local Withdrawal" + (memo.isEmpty() ? "" : ": " + memo))) {
			model.put("successMessage", "Withdrawal Successful");
			return new ModelAndView("main-account-page");
		}
		else {
			model.put("errorMessage", "Invalid balance amount");
			return new ModelAndView("withdraw");
		}
	}

	@GetMapping(value = "/transfer")
	public String showTransferPage(HttpSession session){
		setCurAccount(session);
		return "transfer";
	}
	
	@PostMapping(value = "/transfer")
	public ModelAndView performTransfer(HttpSession session,
										ModelMap model,
										@RequestParam String username,
										@RequestParam String balance,
										@RequestParam String memo){
		setCurAccount(session);
		Account target = service.getAccount(username);
		
		if(target == null) {
			model.put("errorMessage", "User does not exist");
			return new ModelAndView("transfer");
		}
		
		if(updateBalance(false, balance, curAccount, "Transfer to " + username + ": " + memo)) {
			updateBalance(true, balance, target, "Transfer from " + curAccount.getUsername() + (memo.isEmpty() ? "" : ": " + memo));
			model.put("successMessage", "Transfer Successful");
			return new ModelAndView("main-account-page");
		}
		else {
			model.put("errorMessage", "Invalid balance amount");
			return new ModelAndView("transfer");
		}
	}
	
	@GetMapping(value = "/recent-transactions")
	public String showRecentTransactionsPage(ModelMap model, HttpSession session){
		setCurAccount(session);
		model.put("balance", TransactionUtility.parseAmount(curAccount.getBalance()));
		return "recent-transactions";
	}
}
