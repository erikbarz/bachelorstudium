package de.dhbw.demo;

/**
 * This class ...
 * @author Rainer Buesch
 */
public class StringTest {
	
	
	public static final String CENTER = "Center";

	public static void main(String[] args) {
		
		
		String s1 = "Hallo";
		String s2 = "Hallo"+"World";
		
		s1 = s1.replace('H', 'B');
		
		if (s1 == s2) {
			System.out.println(" == is true");
		}
		
		System.out.println("s1 "+s1);
		
		long startTime = System.currentTimeMillis();
		
		
		
		

	}
}
