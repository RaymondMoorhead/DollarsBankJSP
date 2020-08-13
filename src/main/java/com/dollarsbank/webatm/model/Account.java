package com.dollarsbank.webatm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dollarsbank.webatm.security.Encrypt;
import com.dollarsbank.webatm.utility.TransactionUtility;


@Entity
@Table(name = "user")
public class Account {

	@Id
	private String userId;
	@Column
	private String password;
	
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String contactNumber;
	@Column
	private long balance = 0;
	@Column
	private List<String> transactions;

	// used in phone number validation
	private static final String validPhonePatterns;
	private static final Pattern phonePattern;
	
	// PUBLIC STATIC METHODS
	
	public static boolean validPhone(String phoneNum) {
		Matcher matcher = phonePattern.matcher(phoneNum);
		return matcher.matches();
	}
	
	// returns null if everything is fine, otherwise returns the error
	public static String validPassword(String password) {
		// check for minimum length
		if(password.length() < 8)
			return "Less Than 8 Characters";
		
		// check for required characters
		boolean lower = false, upper = false, special = false;
		for(int i = password.length() - 1; i >= 0; --i) {
			if((password.charAt(i) >= 'a') && (password.charAt(i) <= 'z'))
				lower = true;
			else if((password.charAt(i) >= 'A') && (password.charAt(i) <= 'Z'))
				upper = true;
			else
				special = true;
		}
		
		if(!lower)
			return "No Lowercase Characters";
		else if(!upper)
			return "No Uppercase Characters";
		else if(!special)
			return "No Special Characters";
		else
			return null;
	}
	
	public static String generatePassword(String userId, String password) {
		return Encrypt.encrypt(password, userId);
	}
	
	// NON-STANDARD PUBLIC METHODS
	
	// empties the contents of all variables for security purposes
	public void clear() {
		userId 			= "Account.clear() called";
		password 		= "Account.clear() called";
		name 			= "Account.clear() called";
		address 		= "Account.clear() called";
		contactNumber	= "Account.clear() called";
		balance = 0;
		transactions.clear();
	}
	
	public void copy(Account account) {
		userId = account.userId;
		password = account.password;
		name = account.name;
		address = account.address;
		contactNumber = account.contactNumber;
		balance = account.balance;
		transactions = account.transactions;
	}
	
	public boolean correctPassword(String password) {
		return this.password.equals(generatePassword(password));
	}
	
	public void setNewPassword(String password) {
		this.password = generatePassword(password);
	}
	
	public String generatePassword(String password) {
		return generatePassword(password, userId);
	}
	
	public void addAmount(long amount, String message) {
		addTransaction("Added " + TransactionUtility.parseAmount(amount) + " (" + message + ") [" + TransactionUtility.getTime() + "]");
		
		this.balance += amount;
	}
	
	public void subtractAmount(long amount, String message) {
		addTransaction("Removed " + TransactionUtility.parseAmount(amount) + " (" + message + ") [" + TransactionUtility.getTime() + "]");
		this.balance -= amount;
	}
	
	private void addTransaction(String msg) {
		if(transactions.size() == 5)
			transactions.remove(0);
		transactions.add(msg);
	}
	
	// CONSTRUCTORS
	
	public Account(String userId, String password, String name, String address, String contactNumber, long balance, List<String> transactions) {
		super();
		
		if(!validPhone(contactNumber))
			throw new RuntimeException("Invalid contact number supplied to Account constructor");
		
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.balance = balance;
		this.transactions = transactions;
	}
	
	public Account() {
		super();
		transactions = new ArrayList<String>();
	}
	
	// STANDARD PUBLIC METHODS
	
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", contactNumber=" + contactNumber + ", balance=" + balance + ", transactions=" + transactions + "]";
	}
	
	// GETTERS AND SETTERS

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getBalance() {
		return balance;
	}
	
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	public List<String> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}
	
	// NON-PUBLIC
	
	static {
		validPhonePatterns  = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
							+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
							+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		phonePattern = Pattern.compile(validPhonePatterns);
	}
}
