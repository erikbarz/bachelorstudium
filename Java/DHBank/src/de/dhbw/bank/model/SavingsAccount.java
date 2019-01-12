package de.dhbw.bank.model;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class SavingsAccount extends BankAccount {
	
	private double interest;

	public SavingsAccount(String owner, double balance, double interest) throws Exception {
		super(owner, balance);
		setInterest(interest);
	}

	
	public double getInterest() {
		return interest;
	}
	
	public void setInterest(double interest) {
		if (interest < 0 || interest > 100) {
			throw new IllegalArgumentException("Invalid interest: "+interest);
		}
		this.interest = interest;
	}
	
	public void addInterest() {
		double addOn = (getBalance() * interest)/100;
		double newBalance = getBalance() + addOn;
		setBalance(newBalance);
	}
	
	@Override
	public String toString() {
		return super.toString() +", interest="+interest;
	}
}
