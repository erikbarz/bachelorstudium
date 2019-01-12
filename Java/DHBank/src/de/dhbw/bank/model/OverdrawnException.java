package de.dhbw.bank.model;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class OverdrawnException extends Exception {
	
	public OverdrawnException(double amount) {
		super("Attempt to overdraw with amount: "+amount);
	}
}
