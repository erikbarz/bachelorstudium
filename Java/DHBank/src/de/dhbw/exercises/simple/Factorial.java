package de.dhbw.exercises.simple;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class Factorial {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			throw new IllegalArgumentException("You must enter a number");
		}
		
		int number = Integer.parseInt(args[0]);
		int result = 1;
		
		for (int i = number; i > 0; i--) {
			result *= i;
		}
		
		System.out.println("Factorial of "+number+" is: "+result);
	}
}
