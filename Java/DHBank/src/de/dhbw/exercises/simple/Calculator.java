package de.dhbw.exercises.simple;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class Calculator {
	
	public static void main(String[] args) {
		if (args.length < 3) {
			throw new IllegalArgumentException("You must enter: number operator number");
		}
		
		double a = Double.parseDouble(args[0]);
		String operator = args[1];
		double b = Double.parseDouble(args[2]);
	
		double result = 0;
			
		if (operator.equals("+")) {
			result = a + b;
		} 
		else if (operator.equals("-")) {
			result = a - b;
		}
		else if (operator.equals("mal")) {
			result = a * b;
		}
		else if (operator.equals("/")) {
			result = a / b;
		}
		else if (operator.equals("%")) {
			result = a % b;
		}
		else {
			System.out.println("Unsupported operator: "+operator);
			return;
		}
		
		System.out.println("Task: "+a+" "+ operator + " "+b);
		System.out.println("Result: "+result);
	}
}
