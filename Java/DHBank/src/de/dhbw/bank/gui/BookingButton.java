package de.dhbw.bank.gui;

import java.awt.event.*;

import javax.swing.*;

import de.dhbw.bank.model.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public abstract class BookingButton extends SelectionButton {
	
	private final AmountField field;

	public BookingButton(String name, AccountList accList, AmountField field) {
		super(name, accList);
		this.field = field;
		addActionListener(new MyActionListener());
	}

	private class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				double amount = field.getAmount(); // may throw a NumberFormatException or InvalidArgumentException
				BankAccount account = accList.getSelectedValue();
				
				bookValue(account, amount);  // may throw a InvalidAmountException or OverdrawnException
				
				accList.clearSelection();
				accList.setSelectedValue(account, false);
			}
			catch (Exception error) {
				System.out.println("Booking failed: "+ error);
				System.out.println("### start of trace");
				error.printStackTrace();
				System.out.println("### end of trace");
				JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public abstract void bookValue(BankAccount account, double amount) throws InvalidAmountException, OverdrawnException;
}
