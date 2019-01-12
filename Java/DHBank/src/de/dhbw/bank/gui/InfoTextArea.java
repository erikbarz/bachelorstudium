package de.dhbw.bank.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import de.dhbw.bank.model.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class InfoTextArea extends JTextArea  {
	
	private final DHBank bank;
	private final AccountList accList;

	public InfoTextArea(DHBank bank, AccountList accList) {
		super(4, 40);
		this.bank = bank;
		this.accList = accList;
		setEditable(false);
		setBackground(new Color(250,250,200));
		setFont(new Font("Courier", Font.BOLD, 16));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		accList.addListSelectionListener(new MySelectionListener());
	}
	
	private class MySelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			BankAccount account = accList.getSelectedValue();
			if (account == null) {
				setText("");
				return;
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("    Account: ").append(account.getAccountNo()).append('\n');
			sb.append("      Owner: ").append(account.getOwner()).append('\n');
			sb.append("    Balance: ").append(account.getBalance()).append('\n');
			sb.append("Total money: ").append(bank.getTotalMoney());
			setText(sb.toString());
		}
	}

}
