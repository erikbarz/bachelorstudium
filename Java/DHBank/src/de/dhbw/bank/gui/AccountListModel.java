package de.dhbw.bank.gui;

import javax.swing.*;

import de.dhbw.bank.model.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class AccountListModel extends DefaultListModel {
	
	private final DHBank bank;

	public AccountListModel(DHBank bank) {
		this.bank = bank;
	}
	
	public void load() {
		clear();
		for (int i = 0; i < bank.getNumOfAccounts(); i++) {
			addElement(bank.getAccount(i));
		}
	}

}
