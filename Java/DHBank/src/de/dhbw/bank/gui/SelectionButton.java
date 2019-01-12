package de.dhbw.bank.gui;

import javax.swing.*;
import javax.swing.event.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public abstract class SelectionButton extends JButton {
	protected final AccountList accList;
	
	public SelectionButton(String name, AccountList accList) {
		super(name);
		this.accList = accList;
		accList.addListSelectionListener(new MySelectionListener());
		setEnabled(false);
	}
	
	private class MySelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			setEnabled(!accList.isSelectionEmpty());
		}
	}

}
