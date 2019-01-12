package de.dhbw.bank.model;

import java.sql.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public interface Account {
	int getAccountNo();
	void withdraw(double amount) throws Exception;
	void deposit(double amount) throws Exception;
	double getBalance();
	String getOwner();
	Timestamp getCreationTs();
}
