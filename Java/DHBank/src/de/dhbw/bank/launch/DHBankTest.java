package de.dhbw.bank.launch;

import de.dhbw.bank.model.*;

public class DHBankTest {
	
	public static void main(String[] args) {
		
		DHBank bank;
		try {
			bank = new DHBank();
		}
		catch (Exception e) {
			System.out.println("Failed to create bank "+e);
			return;
		}
		double money = bank.getTotalMoney();
		
		
		BankAccount account = bank.getAccount(0);
		try {
			account.deposit(1000);
		}
		catch (Exception e) {
			System.out.println("Deposit Failed "+e);
		}
		
		System.out.println("Before Total "+money);
		System.out.println("Current account "+account);
		
		bank.addInterest();
		
		System.out.println("After Total "+bank.getTotalMoney());
		
		bank.clear();
		
		System.out.println("After clear "+bank.getTotalMoney());

		
	}

}
