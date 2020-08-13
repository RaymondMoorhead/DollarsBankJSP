package com.dollarsbank.webatm.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionUtility {
	
	public static boolean validAmount(String num) {
		try {
			parseAmount(num);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static long parseAmount(String num) {

		int decimal = num.indexOf('.');
		int startIndex = (num.indexOf('$') == -1 ? 0 : 1);
		long amount;
		
		if(decimal == -1)
			amount = Long.parseLong(num.substring(startIndex)) * 100;
		else {
			String beforeDecimal = num.substring(startIndex,  decimal);
			String afterDecimal = num.substring(decimal + 1);
			if(afterDecimal.length() > 2)
				throw new NumberFormatException();
			amount = (Long.parseLong(beforeDecimal) * 100) + Long.parseLong(afterDecimal);
		}
		if(amount < 0)
			throw new NumberFormatException();
		return amount;
	}
	
	public static String parseAmount(long num) {

		String result = Long.toString(num);
		if(result.length() <= 2)
			return "0." + result;
		else {
			return result.substring(0, result.length() - 2) + "." + result.substring(result.length() - 2);
		}
	}
	
	public static String getTime() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
	}
}
