package de.dhbw.bank.gui;

import java.awt.event.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class DeleteButton extends SelectionButton {

	public DeleteButton(AccountList accList) {
		super("Delete", accList);
		addActionListener(new MyActionListener());
	}
	
	private class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			accList.getModel().remove(accList.getSelectedIndex());
		}
	}
}
