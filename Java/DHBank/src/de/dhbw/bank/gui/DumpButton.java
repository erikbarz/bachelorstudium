package de.dhbw.bank.gui;

import java.awt.event.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class DumpButton extends SelectionButton {

	public DumpButton(AccountList accList) {
		super("Dump", accList);
		addActionListener(new MyActionListener());
	}
	
	private class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Selected account: "+accList.getSelectedValue());
		}
	}
}
