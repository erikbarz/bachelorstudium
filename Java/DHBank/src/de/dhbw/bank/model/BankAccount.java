package de.dhbw.bank.model;

import java.sql.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public abstract class BankAccount implements Account, Clearable {
	
	private static int nextAccount;
	
	public static int getCurrentAccountNo() {
		return nextAccount;
	}

	static {
		nextAccount = 1000;
	}
		
	private final int accountNo;
	private final String owner;
	private final Timestamp creationTs;
	
	private double balance;

	public BankAccount(String owner) throws Exception {
		this(owner, 0);
	}
	public BankAccount(String owner, double balance) throws Exception {
		this.accountNo = nextAccount++;
		deposit(balance);
		this.owner = owner;
		creationTs = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getCreationTs() {
		return creationTs;
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	public double getBalance() {
		return balance;
	}
	
	public void clear() {
		balance = 0;
	}
	
	protected void setBalance(double newBalance) {
		this.balance = newBalance;
	}

	public String getOwner() {
		return owner;
	}

	public void deposit(double amount) throws InvalidAmountException {
		checkAmount(amount);
		balance += amount;
	}
	
	public void withdraw(double amount) throws InvalidAmountException, OverdrawnException {
		checkAmount(amount);
		checkOverdrawn(amount);
		balance -= amount;
	}
	
	private void checkAmount(double amount) throws InvalidAmountException {
		if (amount < 0) {
			throw new InvalidAmountException(amount);
		}
	}
	
	protected void checkOverdrawn(double amount) throws OverdrawnException {
		if (amount > balance) {
			throw new OverdrawnException(amount);
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName()+": accountNo="+accountNo+", owner="+owner+", balance="+balance;
	}
}







