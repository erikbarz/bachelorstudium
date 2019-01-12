package de.dhbw.bank.model;

import java.util.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class DHBank implements Bank, Clearable {
		
	private List<BankAccount> accounts = new ArrayList();
	
	public DHBank() throws Exception {
		accounts.add(new CheckingAccount("Dieter", 10, 5000));
		accounts.add(new CheckingAccount("Rudi", 500, 1000));
		accounts.add(new SavingsAccount("Harry", 1500, 4.5));
		accounts.add(new SavingsAccount("Mary", 2500, 3.2));
	}
	
	public void addInterest() {
		for (BankAccount account : accounts) {
			if (account instanceof SavingsAccount) {
				SavingsAccount s = (SavingsAccount)account;
				s.addInterest();
			}
		}
	}
	
	public BankAccount getAccount(int i) {
		return accounts.get(i);
	}

	public int getNumOfAccounts() {
		return accounts.size();
	}

	public double getTotalMoney() {
		double totalBalance = 0;
		for (BankAccount account : accounts) {
			totalBalance += account.getBalance();
		}
		return totalBalance;
	}

	public void clear() {
		for (BankAccount account : accounts) {
			account.clear();
		}
	}
}
