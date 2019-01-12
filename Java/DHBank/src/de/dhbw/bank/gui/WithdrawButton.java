package de.dhbw.bank.gui;

import de.dhbw.bank.model.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class WithdrawButton extends BookingButton {
	
	public WithdrawButton(AccountList accList, AmountField field) {
		super("Withdraw", accList, field);
	}

	@Override
	public void bookValue(BankAccount account, double amount) throws InvalidAmountException, OverdrawnException {
		account.withdraw(amount);
	}

}
