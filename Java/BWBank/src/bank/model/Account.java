package bank.model;

import java.sql.Timestamp;

import library.Book;

public abstract class Account implements Clearable {
	/**
	 * @author Erik
	 * 
	 */

	protected double balance; // Kontostand
	private boolean isLocked = false; // gesperrt
	private final int accountNo; // KontoNr
	private final String owner; // Eigentümer
	protected String log = ""; // Kontoauszug

	private Timestamp creationTS = new Timestamp(System.currentTimeMillis());

	public Account(int accountNo, String owner, double balance) {
		this.accountNo = accountNo;
		this.owner = owner;
		this.balance = balance;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Account) {
			Account a=(Account)obj;
			if(a.accountNo==accountNo){
				return true;
			}
		}
		return false;
	}
	
	public Account(int accountNo, double balance) {
		this.accountNo = accountNo;
		this.owner = "Harry";
		this.balance = balance;
	}

	// einzahlen
	public void deposit(double value) {
		if (value >= 0) {
			balance += value;
			log = log + "Sie haben " + value + " eingezahlt.\n";
		} else {
			throw new IllegalArgumentException("Ihr Wert ist zu niedrig");
		}
	}

	// auszahlen
	public void withdraw(double value) {
		if(isLocked()){
			throw new IllegalArgumentException("Account gesperrt");
		}
		if (balance - value >= 0) {
			balance -= value;
			log = log + "Sie haben sich " + value + " auszahlen lassen.\n";
		} else
			throw new IllegalArgumentException("Ihr Wert ist zu niedrig");
	}

	// kontostand abfragen
	public double getBalance() {
		return balance;
	}

	// kontostand setzen
	public void setBalance(double balance) {
		this.balance = balance;
	}

	// kontoauszug
	public void print() {
		System.out.println("Kontoauszug für " + owner + ", " + creationTS);
		System.out.println("Ihr Kontostand beträgt " + balance + " Euro");
		System.out.println(log);
		log = "";
	}



	// get account
	public int getAccountNo() {
		return accountNo;
	}

	// get owner
	public String getOwner() {
		return owner;
	}

	// get locked
	public boolean isLocked() {
		return isLocked;
	}

	// set locked
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	/*
	 * StringBuilder sb = new StringBuilder();
	 * sb.append(" accNo=").append(accountNo);
	 */

	public String toString() {
		// TODO Auto-generated method stub
		return getClass().getName() + "(owner=" + owner + ",balance=" + balance
				+ ")";
	}

	@Override
	public void clear() {
		balance=0;
		
	}
}
