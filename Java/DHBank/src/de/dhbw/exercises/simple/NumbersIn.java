package de.dhbw.exercises.simple;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class NumbersIn {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			throw new IllegalArgumentException("You must enter a string with numbers in it");
		}
		
		String str = args[0];
		String result = "";
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			result += c;
		}
		
		System.out.println("Found numbers: "+result);
	}
}
