package de.dhbw.bank.gui;

import de.dhbw.bank.model.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class DepositButton extends BookingButton {
	
	public DepositButton(AccountList accList, AmountField field) {
		super("Deposit", accList, field);
	}

	@Override
	public void bookValue(BankAccount account, double amount) throws InvalidAmountException {
		account.deposit(amount);
	}

}
