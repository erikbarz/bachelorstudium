package de.dhbw.bank.model;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class CheckingAccount extends BankAccount {
	
	private double overdrawLimit;

	public CheckingAccount(String owner, double balance, double overdrawLimit) throws Exception {
		super(owner, balance);
		setOverdrawLimit(overdrawLimit);
	}

	
	public double getOverdrawLimit() {
		return overdrawLimit;
	}
	
	@Override
	protected void checkOverdrawn(double amount) throws OverdrawnException {
		if (amount > getBalance() + overdrawLimit) {
			throw new OverdrawnException(amount);
		}
	}
	
	public void setOverdrawLimit(double overdrawLimit) {
		if (overdrawLimit <= 0) {
			throw new IllegalArgumentException("Invalid limit");
		}
		this.overdrawLimit = overdrawLimit;
	}
	
	@Override
	public String toString() {
		return super.toString()+", overdrawLimit="+overdrawLimit;
	}

}
