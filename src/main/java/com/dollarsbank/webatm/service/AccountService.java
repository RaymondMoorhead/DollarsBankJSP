package com.dollarsbank.webatm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollarsbank.webatm.dao.AccountRepository;
import com.dollarsbank.webatm.model.Account;

@Service
public class AccountService {

	public final int maxUserIdLength = 20;
	public final int maxPasswordLength = 20;
	public final int maxNameLength = 60;
	public final int maxAddressLength = 80;
	public final int maxContactNumberLength = 15;
	
	public final int highestMaxLength = Math.max(maxContactNumberLength,
										Math.max(maxUserIdLength,
										Math.max(maxPasswordLength,
										Math.max(maxNameLength,
												 maxAddressLength))));
	
	@Autowired
	private AccountRepository repository;
	private String error = null;
	
	public String getError() {
		String temp = error;
		error = null;
		return temp;
	}
	
	// error is not used here since for security purposes we don't want
	// to let someone know why it failed, by user existence or password
	public boolean validateAccount(String accountId, String password) {
 		return getAccount(accountId, password) != null;
	}
	
	public Account createAccount(String accountId, String password, String name, String address, String contactNumber, long balance) {
		// check for existing user
		if(repository.findByAccountId(accountId) != null) {
			error = "Username already exists";
			return null;
		}
		else if((error = Account.validPassword(password)) != null)
			return null;
		else if(!Account.validPhone(contactNumber)) {
			error = "Invalid Phone Number Syntax";
			return null;
		}
		
		Account account = new Account();
		account.setName(name);
		account.setAccountId(accountId);
		account.setNewPassword(password); // encrypts the password
		account.setAddress(address);
		account.setContactNumber(contactNumber);
		account.addAmount(balance, "Initial Deposit"); // adds to transaction history
		repository.save(account);
		return account;
	}
	
	public Account getAccount(String accountId, String password) {
		return repository.findByAccountIdAndPassword(accountId, Account.generatePassword(accountId, password));
	}
}
