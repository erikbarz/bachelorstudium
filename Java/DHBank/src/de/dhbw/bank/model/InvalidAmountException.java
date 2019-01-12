package de.dhbw.bank.model;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class InvalidAmountException extends Exception {
	
	public InvalidAmountException(double amount) {
		super("Invalid negative amount: "+amount);
	}
}
