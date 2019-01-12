package de.dhbw.bank.model;

import java.util.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class Person {
	
	private final Date birthDate;
	private final String foreName;
	private final String lastName;
	private final boolean isMale;
	
	private String address;

	public Person(Date birthDate, String foreName, String lastName, boolean isMale, String address) {
		this.birthDate = birthDate;
		this.foreName = foreName;
		this.lastName = lastName;
		this.isMale = isMale;
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getBirthDate() {
		return (Date) birthDate.clone();
	}
	
	public String getForeName() {
		return foreName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public boolean isMale() {
		return isMale;
	}
	
	@Override
	public String toString() {
		return foreName+ " "+lastName;
	}
}
