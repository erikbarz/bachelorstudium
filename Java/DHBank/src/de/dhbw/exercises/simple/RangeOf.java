package de.dhbw.exercises.simple;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class RangeOf {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			throw new IllegalArgumentException("You must enter a list of numbers");
		}
		
		double minValue = Double.MAX_VALUE;
		double maxValue = Double.MIN_VALUE;
		
		for (int i = 0; i < args.length; i++) {
			double value = Double.parseDouble(args[i]);
			minValue = Math.min(value, minValue);
			maxValue = Math.max(value, maxValue);
		}
		
		System.out.println("MinValue: "+minValue);
		System.out.println("MaxValue: "+maxValue);
	}
}
