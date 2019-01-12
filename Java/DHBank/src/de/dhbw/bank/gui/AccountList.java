package de.dhbw.bank.gui;

import java.awt.*;

import javax.swing.*;

import de.dhbw.bank.model.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class AccountList extends JList {
	
	public AccountList(DHBank bank) {
		super(new AccountListModel(bank));
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setCellRenderer(new AccountRenderer());
	}
	
	public void load() {
		getModel().load();
	}
	
	@Override
	public AccountListModel getModel() {
		return (AccountListModel) super.getModel();
	}
	
	@Override
	public BankAccount getSelectedValue() {
		return (BankAccount) super.getSelectedValue();
	}	
	
	private class AccountRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			BankAccount acc = (BankAccount)value;
			
			setText(acc.getOwner()+" ("+acc.getBalance()+")");
			if (isSelected) {
				setBackground(Color.yellow);
			} else {
				setBackground(Color.white);
			}
			
			return this;
		}	
	}


}
