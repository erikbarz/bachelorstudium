package de.dhbw.bank.model;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public interface Bank {
	
	double getTotalMoney();
	
	int getNumOfAccounts();
	
	BankAccount getAccount(int i);

}
