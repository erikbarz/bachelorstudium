package de.dhbw.exercises.simple;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class Area {
	
	public static void main(String[] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException("You must enter width and height");
		}
		
		double width = Double.parseDouble(args[0]);
		double height = Double.parseDouble(args[1]);
		double area = width * height;
		
		System.out.println("Area: "+area);
	}
}
